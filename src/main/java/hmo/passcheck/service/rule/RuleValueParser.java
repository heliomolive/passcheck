package hmo.passcheck.service.rule;

import hmo.passcheck.domain.exception.InternalErrorException;

import static java.lang.String.format;
import static java.util.Objects.isNull;

public class RuleValueParser {

    public static <I> Integer parseIntRuleValue(I ruleValue) {
        if (isNull(ruleValue)) {
            throw internalErrorException(null, null);
        }
        try {
            return Integer.parseInt(ruleValue.toString());
        } catch (Exception e) {
            throw internalErrorException(ruleValue, e);
        }
    }

    private static InternalErrorException internalErrorException(Object ruleValue, Exception cause) {
        return InternalErrorException.builder()
                .developerMessage(format("Invalid Rule Value [%s] can not be parsed as integer type.", ruleValue))
                .cause(cause)
                .build();
    }
}
