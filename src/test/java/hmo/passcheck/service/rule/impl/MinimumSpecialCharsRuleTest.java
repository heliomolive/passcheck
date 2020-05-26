package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRuleResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimumSpecialCharsRuleTest {

    private static final String ERROR_MESSAGE = "Número insuficiente de caracteres especiais";

    private MinimumSpecialCharsRule fixture = new MinimumSpecialCharsRule(ERROR_MESSAGE);


    @Test
    public void validate() {
        String password = "asdfg@";
        int minSpecialChars = 1;

        PassRuleResult passRuleResult = fixture.validate(password, minSpecialChars);

        assertNotNull(passRuleResult);
        assertTrue(passRuleResult.getValid());
    }

    @Test
    public void validateWhenNumberOfSpecialCharsIsSmallerThanExpected() {
        String password = "asdfg@";
        int minSpecialChars = 12;

        PassRuleResult passRuleResult = fixture.validate(password, minSpecialChars);

        assertNotNull(passRuleResult);
        assertFalse(passRuleResult.getValid());
        assertEquals(ERROR_MESSAGE, passRuleResult.getMessage());
    }
}
