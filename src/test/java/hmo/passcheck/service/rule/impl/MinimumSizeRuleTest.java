package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRuleResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimumSizeRuleTest {

    private static final String ERROR_MESSAGE = "Tamanho mínimo inválido";

    private MinimumSizeRule fixture = new MinimumSizeRule(ERROR_MESSAGE);


    @Test
    public void validate() {
        String password = "123456789";
        int minimumSize = 9;

        PassRuleResult passRuleResult = fixture.validate(password, minimumSize);

        assertNotNull(passRuleResult);
        assertTrue(passRuleResult.getValid());
    }

    @Test
    public void validateWhenPassIsSmallerThanMinimumSize() {
        String password = "12345678";
        int minimumSize = 9;

        PassRuleResult passRuleResult = fixture.validate(password, minimumSize);

        assertNotNull(passRuleResult);
        assertFalse(passRuleResult.getValid());
        assertEquals(ERROR_MESSAGE, passRuleResult.getMessage());
    }
}
