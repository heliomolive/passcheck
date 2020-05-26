package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRule;
import hmo.passcheck.service.rule.PassRuleResult;

import static hmo.passcheck.service.rule.RuleValueParser.parseIntRuleValue;
import static hmo.passcheck.service.rule.impl.AsciiCharsCounter.countOccurrences;
import static java.lang.String.format;

public class MinimumLowercaseCharsRule implements PassRule {

    private static final int LOWERCASE_A_ASCII = 97;
    private static final int LOWERCASE_Z_ASCII = 122;
    private String rejectMessage;

    public MinimumLowercaseCharsRule(String errorMessage, Object... messageParams) {
        rejectMessage = format(errorMessage, messageParams);
    }

    @Override
    public <T> PassRuleResult validate(String password, T ruleValue) {

        int minOccurrences = parseIntRuleValue(ruleValue);
        int occurrences = countOccurrences(password.toCharArray(), LOWERCASE_A_ASCII, LOWERCASE_Z_ASCII);
        if (occurrences<minOccurrences) {
            return PassRuleResult.builder()
                    .valid(false)
                    .message(rejectMessage)
                    .build();
        }
        return PassRuleResult.builder().valid(true).build();
    }
}
