package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRule;
import hmo.passcheck.service.rule.PassRuleResult;

import static hmo.passcheck.service.rule.RuleValueParser.parseIntRuleValue;
import static hmo.passcheck.service.rule.impl.AsciiCharsCounter.countOccurrences;
import static java.lang.String.format;

public class MinimumUppercaseCharsRule implements PassRule {

    private static final int UPPERCASE_A_ASCII = 65;
    private static final int UPPERCASE_Z_ASCII = 90;
    private String rejectMessage;

    public MinimumUppercaseCharsRule(String errorMessage, Object... messageParams) {
        rejectMessage = format(errorMessage, messageParams);
    }

    @Override
    public <T> PassRuleResult validate(String password, T ruleValue) {

        int minOccurrences = parseIntRuleValue(ruleValue);
        int occurrences = countOccurrences(password.toCharArray(), UPPERCASE_A_ASCII, UPPERCASE_Z_ASCII);
        if (occurrences<minOccurrences) {
            return PassRuleResult.builder()
                    .valid(false)
                    .message(rejectMessage)
                    .build();
        }
        return PassRuleResult.builder().valid(true).build();
    }
}
