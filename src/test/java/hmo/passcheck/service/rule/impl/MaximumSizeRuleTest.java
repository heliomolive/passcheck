package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRuleResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MaximumSizeRuleTest {

    private static final String ERROR_MESSAGE = "Tamanho máximo inválido";

    private MaximumSizeRule fixture = new MaximumSizeRule(ERROR_MESSAGE);


    @Test
    public void validate() {
        String password = "123456789";
        int ruleValue = 9;

        PassRuleResult passRuleResult = fixture.validate(password, ruleValue);

        assertNotNull(passRuleResult);
        assertTrue(passRuleResult.getValid());
    }

    @Test
    public void validateWhenPasswordIsBiggerThanMaxSize() {

        PassRuleResult passRuleResult = fixture.validate("123456789", 8);

        assertNotNull(passRuleResult);
        assertFalse(passRuleResult.getValid());
        assertEquals(ERROR_MESSAGE, passRuleResult.getMessage());
    }
}
