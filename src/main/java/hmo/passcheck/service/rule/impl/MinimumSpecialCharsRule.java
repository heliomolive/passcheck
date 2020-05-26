package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRule;
import hmo.passcheck.service.rule.PassRuleResult;

import static hmo.passcheck.service.rule.RuleValueParser.parseIntRuleValue;
import static hmo.passcheck.service.rule.impl.AsciiCharsCounter.countOccurrences;
import static java.lang.String.format;

public class MinimumSpecialCharsRule implements PassRule {

    private static final int EXCLAMATION_ASCII = 33;
    private static final int SLASH_ASCII = 47;

    private static final int COLON_ASCII = 58;
    private static final int AT_SYMBOL_ASCII = 64;

    private static final int OPENING_BRACKET_ASCII = 91;
    private static final int GRAVE_ACCENT_ASCII = 96;

    private static final int OPENING_BRACE_ASCII = 123;
    private static final int TILDE_ASCII = 126;

    private String rejectMessage;

    public MinimumSpecialCharsRule(String errorMessage, Object... messageParams) {
        rejectMessage = format(errorMessage, messageParams);
    }

    @Override
    public <T> PassRuleResult validate(String password, T ruleValue) {

        int minOccurrences = parseIntRuleValue(ruleValue);
        char[] chars = password.toCharArray();

        int occurrences = countOccurrences(chars, EXCLAMATION_ASCII, SLASH_ASCII);
        occurrences += countOccurrences(chars, COLON_ASCII, AT_SYMBOL_ASCII);
        occurrences += countOccurrences(chars, OPENING_BRACKET_ASCII, GRAVE_ACCENT_ASCII);
        occurrences += countOccurrences(chars, OPENING_BRACE_ASCII, TILDE_ASCII);

        if (occurrences<minOccurrences) {
            return PassRuleResult.builder()
                    .valid(false)
                    .message(rejectMessage)
                    .build();
        }
        return PassRuleResult.builder().valid(true).build();
    }
}
