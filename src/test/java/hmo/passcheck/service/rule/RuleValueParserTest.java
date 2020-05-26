package hmo.passcheck.service.rule;

import hmo.passcheck.domain.exception.InternalErrorException;
import org.junit.jupiter.api.Test;

import static hmo.passcheck.service.rule.RuleValueParser.parseIntRuleValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RuleValueParserTest {

    @Test
    public void parseIntRuleValueWhenParameterIsNumber() {
        Integer value = 123;

        Integer result = parseIntRuleValue(value);

        assertEquals(value, result);
    }

    @Test
    public void parseIntRuleValueWhenParameterIsString() {
        String value = "123";

        Integer result = parseIntRuleValue(value);

        assertEquals(Integer.parseInt(value), result);
    }

    @Test
    public void parseIntRuleValueWhenRuleValueIsNotANumber() {
        assertThrows(InternalErrorException.class, () -> parseIntRuleValue("xpto"));
    }

    @Test
    public void parseIntRuleValueWhenRuleValueIsNull() {
        assertThrows(InternalErrorException.class, () -> parseIntRuleValue(null));
    }
}
