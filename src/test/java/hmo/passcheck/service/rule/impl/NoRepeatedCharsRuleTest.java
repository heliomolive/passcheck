package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRuleResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoRepeatedCharsRuleTest {

    private static final String ERROR_MESSAGE = "Não são permitidos caracteres repetidos";

    private NoRepeatedCharsRule fixture = new NoRepeatedCharsRule(ERROR_MESSAGE);

    @Test
    public void validate() {
        String password = "asdfg12345#@";

        PassRuleResult passRuleResult = fixture.validate(password, null);

        assertNotNull(passRuleResult);
        assertTrue(passRuleResult.getValid());
    }

    @Test
    public void validateWhenThereIsRepeatedLetters() {
        String password = "asdfg12345#@a";

        PassRuleResult passRuleResult = fixture.validate(password, null);

        assertNotNull(passRuleResult);
        assertFalse(passRuleResult.getValid());
        assertEquals(ERROR_MESSAGE, passRuleResult.getMessage());
    }

    @Test
    public void validateWhenThereIsRepeatedDigits() {
        String password = "asdfg12345#5";

        PassRuleResult passRuleResult = fixture.validate(password, null);

        assertNotNull(passRuleResult);
        assertFalse(passRuleResult.getValid());
        assertEquals(ERROR_MESSAGE, passRuleResult.getMessage());
    }

    @Test
    public void validateWhenThereIsRepeatedSpecialChars() {
        String password = "asdfg12345##";

        PassRuleResult passRuleResult = fixture.validate(password, null);

        assertNotNull(passRuleResult);
        assertFalse(passRuleResult.getValid());
        assertEquals(ERROR_MESSAGE, passRuleResult.getMessage());
    }
}
