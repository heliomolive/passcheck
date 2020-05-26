package hmo.passcheck.service.rule;

import hmo.passcheck.domain.enums.RuleName;
import hmo.passcheck.domain.exception.InternalErrorException;
import hmo.passcheck.service.rule.impl.MaximumSizeRule;
import hmo.passcheck.service.rule.impl.MinimumDigitOccurrencesRule;
import hmo.passcheck.service.rule.impl.MinimumLowercaseCharsRule;
import hmo.passcheck.service.rule.impl.MinimumSizeRule;
import hmo.passcheck.service.rule.impl.MinimumSpecialCharsRule;
import hmo.passcheck.service.rule.impl.MinimumUppercaseCharsRule;
import hmo.passcheck.service.rule.impl.NoRepeatedCharsRule;

import static java.lang.String.format;

public class PassRuleFactory {

    public static PassRule getPassRule(RuleName ruleName, String rejectMessage, Object... rejectMessageParams) {
        switch (ruleName) {
            case  MIN_PASS_SIZE :
                return new MinimumSizeRule(rejectMessage, rejectMessageParams);
            case  MAX_PASS_SIZE :
                return new MaximumSizeRule(rejectMessage, rejectMessageParams);
            case  MIN_DIGIT_OCCURRENCES :
                return new MinimumDigitOccurrencesRule(rejectMessage, rejectMessageParams);
            case  MIN_LOWERCASE_CHARS :
                return new MinimumLowercaseCharsRule(rejectMessage, rejectMessageParams);
            case  MIN_UPPERCASE_CHARS :
                return new MinimumUppercaseCharsRule(rejectMessage, rejectMessageParams);
            case  MIN_SPECIAL_CHARS :
                return new MinimumSpecialCharsRule(rejectMessage, rejectMessageParams);
            case  NO_REPEATED_CHARS :
                return new NoRepeatedCharsRule(rejectMessage);
            default :
                throw InternalErrorException.builder()
                        .developerMessage(format("Rule implementation not found [%s]", ruleName))
                        .build();
        }
    }
}
