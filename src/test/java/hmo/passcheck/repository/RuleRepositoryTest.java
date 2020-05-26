package hmo.passcheck.repository;

import hmo.passcheck.domain.enums.RuleName;
import hmo.passcheck.mother.RuleMother;
import hmo.passcheck.repository.entity.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class RuleRepositoryTest {

    @Autowired
    private RuleRepository fixture;

    @Test
    public void testEntityMapping() {
        Rule saved = findOrCreate();

        Optional<Rule> found = fixture.findById(saved.getRuleId());

        assertTrue(found.isPresent());
        assertEquals(saved, found.get());
    }

    private Rule findOrCreate() {
        Optional<Rule> rule = fixture.findOne(Example.of(
                Rule.builder().ruleName(RuleName.MIN_PASS_SIZE).build()));

        return rule.orElseGet(() -> fixture.saveAndFlush(RuleMother.getRuleForMinimumSize(null)));
    }
}
