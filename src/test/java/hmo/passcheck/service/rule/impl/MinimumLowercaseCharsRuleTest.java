package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRuleResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimumLowercaseCharsRuleTest {

    private static final String ERROR_MESSAGE = "Número mínimo de letras minúsculas insuficiente";

    private MinimumLowercaseCharsRule fixture = new MinimumLowercaseCharsRule(ERROR_MESSAGE);


    @Test
    public void validate() {
        String password = "a12345";
        int minimumLowercase = 1;

        PassRuleResult passRuleResult = fixture.validate(password, minimumLowercase);

        assertNotNull(passRuleResult);
        assertTrue(passRuleResult.getValid());
    }

    @Test
    public void validateWhenNumberOfLowercaseCharsIsSmallerThanExpected() {
        String password = "a12345";
        int minimumLowercase = 2;

        PassRuleResult passRuleResult = fixture.validate(password, minimumLowercase);

        assertNotNull(passRuleResult);
        assertFalse(passRuleResult.getValid());
        assertEquals(ERROR_MESSAGE, passRuleResult.getMessage());
    }
}
