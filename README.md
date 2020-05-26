# API passcheck
API para validação de formato de senhas.

Esta aplicação foi criada com Spring Boot e requer um banco de dados. Para efeito de demonstração, já está configurada com um banco embutido H2.

## Execução da aplicação
A aplicação pode ser executada através do arquivo JAR:
> java -jar passcheck-0.0.1-SNAPSHOT.jar

Também podemos executá-la com o Gradle:
> ./gradlew bootRun

Ou mesmo com o suporte de uma IDE. Na implementação foi utilizado o IntelliJ.

## Apresentação da API e uso do endpoint
A documentação da API é gerada automaticamente pelo Swagger, podendo ser acessada no endereço:
> http://localhost:8080/passcheck/swagger-ui.html

Nesta versão inicial, somente um enpoint foi disponibilizado:
> POST http://localhost:8080/passcheck/v1/validate

Este endpoint espera uma requisição JSON no seguinte formato (favor declarar o _header_ `Content-Type: application/json`):
> {
  	"password":"aBcdefghij&1234",
  	"passwordClass":"NCPWR"
  }

O parâmetro `password` trata-se da senha a ser validada e é obrigatório. O `passwordClass` é opcional e indica a classe de senhas a ser usada na validação (quando omitido, é utilizada a classe **NCPWR**).

Caso a senha seja válida, o endpoint retorna o código HTTP 204 -- o corpo da resposta é vazio pois o próprio código HTTP já indica que a senha possui formato válido. 

Se a senha for inválida, o retorno apresentará o código HTTP 422 com uma resposta JSON no seguinte formato:
> {
      "developerMessage": "Invalid password format.",
      "userMessage": "Senha inválida, verifique as regras a seguir: [Não são permitidos caracteres repetidos]"
  } 

O atributo `developerMessage` apresenta uma mensagem mais técnica, podendo ser utizado pelo desenvolvedor responsável pela aplicação que vai consumir a API. O `userMessage` é uma mensagem que pode ser exibida ao usuário final -- para o caso de senha inválida, este atributo lista todas as regras violadas pela senha.

## Detalhes da solução adotada

**Classes de senhas**

Com o objetivo de oferecer suporte à validação de diferentes categorias de senhas, a aplicação permite a criação de _classes de senhas_. Uma classe possui um conjunto de regras que a senha deve atender para ser considerada válida naquela classe.

Inicialmente foi definida a classe NCPWR (Nine Chars Password Without Repetition), contendo as seguintes regras:
* Mínimo de nove caracteres
* Máximo de vinte caracteres
* Mínimo de um dígito
* Mínimo de uma letra minúscula
* Mínimo de uma letra maiúscula
* Mínimo de um caractere especial
* Não possuir caracteres repetidos

Tais regras compõem o conjunto solicitado para o projeto de validação de senhas, com inclusão da regra de número máximo de caracteres.

A classe de senhas e suas regras são cadastradas no banco de dados e podem ser alteradas conforme desejado. Por exemplo, podemos alterar o número mínimo de dígitos ajustando o valor da respectiva regra no banco, ou mesmo remover da classe uma de suas regras.

Para criar uma nova classe de senhas, é necessário cadastrá-la no banco e também no _enum_ `PassClassName`. A nova classe pode reutilizar as regras já existentes na aplicação, apenas definindo seus próprios valores no banco de dados.

Caso seja necessário criar alguma regra diferente (por exemplo, uma regra para definir o número máximo de dígitos ou uma regra baseada em expressão regular), além de cadastrar a regra no banco, é preciso criar a sua implementação na aplicação.

**Modelo de dados**

As seguintes tabelas compõem o modelo de dados da aplicação:
* `PASS_CLASS`: classes de senhas existentes na aplicação. O campo `PASS_CLASS_NAME` é um acrônimo que também precisa ser criado no _enum_ `PassClassName`.
* `RULE`: regras de validação implementadas. As regras são independentes das classes de senhas, podendo ser reutilizadas por várias classes. O campo `REJECT_MESSAGE` mantém a mensagem de erro a ser retornada para o usuário, caso a senha não atenda à respectiva regra. O campo `RULE_VALUE_TYPE` é utilizado para regras parametrizadas e indica o tipo de dados esperado do parâmetro. 
* `CLASS_RULE`: lista de regras de cada classe de senhas. Nesta tabela são informados a ordem de aplicação das regras e seus valores (para regras parametrizadas).

Como comentado anteriormente, a aplicação está configurada com um banco de dados embutido, para efeito de demonstração. As tabelas são criadas automaticamente pelo Flyway quando a aplicação é iniciada. Também foram incluídos nos scripts do Flyway comandos SQL para cadastrar a classe de senhas NCPWR e suas regras. 

**Visão geral da arquitetura**

A aplicação está organizada em camadas: _WEB controller_, serviço e repositórios. 

Na camada do _controller_ foi criado um _Controller Advice_ para o tratamento de exceções, incluindo _log_ das mesmas. Na camada de serviço, os _services_ implementados não definem interface pois, tratando-se de um microserviço, entendemos não haver necessidade (a única implementação dos serviços apresentados é fornecida pela própria API **passcheck**).

A camada de repositórios foi implementada com JPA. Não foi feito tratamento transacional na camada de serviço pois os operações implementadas até o momento fazem apenas leitura no banco de dados.

Foi criado um cache para manter em memória as classes de senhas existentes. Desta forma, melhoramos o desempenho da aplicação pois não é necessário carregar do banco de dados as classes e regras a cada execução do endpoint de validação. 

Para facilitar o desenvolvimento e melhorar a legibilidade do código, bibliotecas de geração automática de código foram utilizadas (Lombok e MapStruct). 

**Testes automatizados**

A estratégia de testes utilizada foi criar testes em cada camada da aplicação:
* Testes de repositório: validam o mapeamento objeto-relacional das entidades e os métodos criados nos repositórios.
* Testes na camada de serviço: são testes unitários que utilizam apenas JUnit (assim executam com melhor desempenho, por não carregarem o contexto do Spring) e criam _mocks_ das suas dependências.
* Testes na camada de _controller_: foram criados com suporte da anotação `@WebMvcTest`, para carregar somente o contexto mínimo necessário do Spring, e também utilizam _mocks_.
* Testes de integração: utilizam a anotação `@SpringBootTest` para carregar o contexto do Spring e executar testes que percorrem todas as camadas da aplicação.

