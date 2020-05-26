package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRuleResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimumUppercaseCharsRuleTest {

    private static final String ERROR_MESSAGE = "Número mínimo de letras maiúsculas insuficiente";

    private MinimumUppercaseCharsRule fixture = new MinimumUppercaseCharsRule(ERROR_MESSAGE);

    @Test
    public void validate() {
        String password = "abcD1234";
        int minimumUppercase = 1;

        PassRuleResult passRuleResult = fixture.validate(password, minimumUppercase);

        assertNotNull(passRuleResult);
        assertTrue(passRuleResult.getValid());
    }

    @Test
    public void validateWhenNumberOfUpperCharsIsSmallerThanExpected() {
        String password = "abcD1234";
        int minimumUppercase = 2;

        PassRuleResult passRuleResult = fixture.validate(password, minimumUppercase);

        assertNotNull(passRuleResult);
        assertFalse(passRuleResult.getValid());
        assertEquals(ERROR_MESSAGE, passRuleResult.getMessage());
    }
}
