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

Este endpoint espera uma requisição JSON no seguinte formato:
> {
  	"password":"aBcdefghij&1234",
  	"passwordClass":"NCPWR"
  }

O parâmetro `password` trata-se da senha a ser validada e é obrigatório. O `passwordClass` é opcional e indica a classe de senhas a ser usada na validação (quando omitido, é utilizada a classe **NCPWR**).

Caso a senha seja válida, o endpoint retorna o código HTTP 204 -- o corpo da resposta é vazio pois o próprio código HTTT já indica que a senha possui formato válido. 

Se a senha for inválida, o retorno apresentará o código HTPP 422 com uma resposta JSON no seguinte formato:
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

