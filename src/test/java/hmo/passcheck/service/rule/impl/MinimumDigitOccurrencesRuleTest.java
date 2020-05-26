package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRuleResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimumDigitOccurrencesRuleTest {

    private static final String ERROR_MESSAGE = "Número mínimo de dígitos insuficiente";

    private MinimumDigitOccurrencesRule fixture = new MinimumDigitOccurrencesRule(ERROR_MESSAGE);


    @Test
    public void validate() {
        String password = "asdf1";
        Integer minimumDigitOccurrences = 1;

        PassRuleResult passRuleResult = fixture.validate(password, minimumDigitOccurrences);

        assertNotNull(passRuleResult);
        assertTrue(passRuleResult.getValid());
    }

    @Test
    public void validateWhenOccurrencesIsSmallerThanExpected() {
        String password = "asdf1";
        Integer minimumDigitOccurrences = 2;

        PassRuleResult passRuleResult = fixture.validate(password, minimumDigitOccurrences);

        assertNotNull(passRuleResult);
        assertFalse(passRuleResult.getValid());
        assertEquals(ERROR_MESSAGE, passRuleResult.getMessage());
    }
}
