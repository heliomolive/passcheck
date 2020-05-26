package hmo.passcheck.repository;

import hmo.passcheck.domain.enums.PassClassName;
import hmo.passcheck.domain.enums.RuleName;
import hmo.passcheck.mother.ClassRuleMother;
import hmo.passcheck.mother.PassClassMother;
import hmo.passcheck.mother.RuleMother;
import hmo.passcheck.repository.entity.ClassRule;
import hmo.passcheck.repository.entity.PassClass;
import hmo.passcheck.repository.entity.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ClassRuleRepositoryTest {

    @Autowired
    private ClassRuleRepository fixture;

    @Autowired
    private PassClassRepository passClassRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Test
    public void saveClassRule() {
        PassClass passClass = findOrCreatePassClass();
        Rule rule = findOrCreateRule();
        ClassRule saved = findOrCreateClassRule(passClass, rule);

        Optional<ClassRule> found = fixture.findById(saved.getClassRuleId());

        assertTrue(found.isPresent());
        assertEquals(saved, found.get());
    }

    private PassClass findOrCreatePassClass() {
        Optional<PassClass> passClass = passClassRepository.findOne(Example.of(
                PassClass.builder().passClassName(PassClassName.NCPWR).build()));
        return passClass.orElseGet(() -> passClassRepository.saveAndFlush(PassClassMother.getPassClass(null)));
    }

    private Rule findOrCreateRule() {
        Optional<Rule> rule = ruleRepository.findOne(Example.of(
                Rule.builder().ruleName(RuleName.MIN_PASS_SIZE).build()));
        return rule.orElseGet(() -> ruleRepository.saveAndFlush(RuleMother.getRuleForMinimumSize(null)));
    }

    private ClassRule findOrCreateClassRule(PassClass passClass, Rule rule) {
        Optional<ClassRule> classRule = fixture.findOne(Example.of(
                ClassRule.builder().passClass(passClass).rule(rule).build()));
        return classRule.orElseGet(() -> fixture.saveAndFlush(
                ClassRuleMother.getClassRule(null, passClass, rule, "8")));
    }
}
