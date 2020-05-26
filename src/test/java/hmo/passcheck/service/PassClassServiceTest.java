package hmo.passcheck.service;

import hmo.passcheck.domain.dto.PassClassDto;
import hmo.passcheck.domain.enums.PassClassName;
import hmo.passcheck.domain.mapper.ClassRuleMapper;
import hmo.passcheck.domain.mapper.PassClassMapper;
import hmo.passcheck.mother.ClassRuleMother;
import hmo.passcheck.mother.PassClassMother;
import hmo.passcheck.mother.RuleMother;
import hmo.passcheck.repository.PassClassRepository;
import hmo.passcheck.repository.entity.ClassRule;
import hmo.passcheck.repository.entity.PassClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PassClassServiceTest {

    @InjectMocks
    private PassClassService fixture;

    @Mock
    private PassClassRepository passClassRepository;

    private PassClassMapper passClassMapper = Mappers.getMapper(PassClassMapper.class);
    private ClassRuleMapper classRuleMapper = Mappers.getMapper(ClassRuleMapper.class);

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        fixture.setPassClassMapper(passClassMapper);
    }

    @Test
    public void findPassClass(){
        PassClass passClass = PassClassMother.getPassClass(899L);

        ClassRule classRule1 = ClassRuleMother.getClassRule(1L, passClass,
                RuleMother.getRuleForMinimumSize(1L), "8", 1);
        ClassRule classRule2 = ClassRuleMother.getClassRule(2L, passClass,
                RuleMother.getRuleForMaximumSize(2L), "20", 2);
        passClass.getRules().add(classRule2);
        passClass.getRules().add(classRule1);

        when(passClassRepository.findByPassClassName(any(PassClassName.class))).thenReturn(Optional.of(passClass));

        PassClassDto passClassDto = fixture.findPassClass(passClass.getPassClassName());

        assertNotNull(passClassDto);
        assertEquals(passClass.getPassClassId(), passClassDto.getPassClassId());
        assertEquals(passClass.getPassClassName(), passClassDto.getPassClassName());
        assertEquals(passClass.getPassClassDesc(), passClassDto.getPassClassDesc());

        assertEquals(2, passClassDto.getRules().size());
        assertEquals(classRuleMapper.getClassRuleDto(classRule1), passClassDto.getRules().get(0));
        assertEquals(classRuleMapper.getClassRuleDto(classRule2), passClassDto.getRules().get(1));
    }
}
