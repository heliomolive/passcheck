package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRule;
import hmo.passcheck.service.rule.PassRuleResult;

import static hmo.passcheck.service.rule.RuleValueParser.parseIntRuleValue;
import static hmo.passcheck.service.rule.impl.AsciiCharsCounter.countOccurrences;
import static java.lang.String.format;

public class MinimumDigitOccurrencesRule implements PassRule {

    private static final int DIGIT_0_ASCII = 48;
    private static final int DIGIT_9_ASCII = 57;

    private String rejectMessage;

    public MinimumDigitOccurrencesRule(String errorMessage, Object... messageParams) {
        rejectMessage = format(errorMessage, messageParams);
    }

    @Override
    public <T> PassRuleResult validate(String password, T ruleValue) {

        int minOccurrences = parseIntRuleValue(ruleValue);
        int occurrences = countOccurrences(password.toCharArray(), DIGIT_0_ASCII, DIGIT_9_ASCII);
        if (occurrences<minOccurrences) {
            return PassRuleResult.builder()
                    .valid(false)
                    .message(rejectMessage)
                    .build();
        }
        return PassRuleResult.builder().valid(true).build();
    }
}
