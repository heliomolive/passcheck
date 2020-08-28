package hmo.passcheck.service;

import hmo.passcheck.domain.exception.UnprocessableEntityException;
import hmo.passcheck.domain.mapper.PassClassMapper;
import hmo.passcheck.mother.ClassRuleMother;
import hmo.passcheck.mother.PassClassMother;
import hmo.passcheck.mother.RuleMother;
import hmo.passcheck.repository.entity.ClassRule;
import hmo.passcheck.repository.entity.PassClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PassCheckServiceTest {

    private static final String USER_MESSAGE_PREFIX = "Senha inválida, verifique as regras a seguir: %s";
    private static final String PASS_CLASS_NAME = "NCPWR";

    @InjectMocks
    private PassCheckService fixture;

    @Mock
    private PassClassService passClassService;

    private PassClassMapper passClassMapper = Mappers.getMapper(PassClassMapper.class);


    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        fixture.setInvalidPasswordUserMsg(USER_MESSAGE_PREFIX);
    }

    @Test
    public void validate() {
        String password = "asdF1234!";
        PassClass passClass = createPassClass();
        when(passClassService.findPassClass(anyString())).thenReturn(
                passClassMapper.getPassClassDto(passClass));

        fixture.validate(password, PASS_CLASS_NAME);

        verify(passClassService).findPassClass(eq(passClass.getPassClassName()));
    }

    @Test
    public void validateWhenPasswordIsInvalid() {
        String password = "asdF12!";
        PassClass passClass = createPassClass();
        when(passClassService.findPassClass(anyString())).thenReturn(
                passClassMapper.getPassClassDto(passClass));

        assertThrows(UnprocessableEntityException.class,
                () -> fixture.validate(password, PASS_CLASS_NAME));

        verify(passClassService).findPassClass(eq(passClass.getPassClassName()));
    }

    private PassClass createPassClass() {
        PassClass passClass = PassClassMother.getPassClass(1L);
        List<ClassRule> classRules = new ArrayList<>();
        classRules.add(ClassRuleMother.getClassRule(1L, passClass,
                RuleMother.getRuleForMinimumSize(1L), "8", 1));
        classRules.add(ClassRuleMother.getClassRule(2L, passClass,
                RuleMother.getRuleForMaximumSize(2L), "20", 2));
        passClass.setRules(classRules);

        return passClass;
    }

}
