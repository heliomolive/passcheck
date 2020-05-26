package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRule;
import hmo.passcheck.service.rule.PassRuleResult;

import static hmo.passcheck.service.rule.impl.AsciiCharsCounter.countOccurrences;

public class NoRepeatedCharsRule implements PassRule {

    private String rejectMessage;

    public NoRepeatedCharsRule(String errorMessage) {
        rejectMessage = errorMessage;
    }

    @Override
    public <T> PassRuleResult validate(String password, T ruleValue) {

        char[] chars = password.toCharArray();

        for (int i=0; i<chars.length-1; i++) {
            if (countOccurrences(chars, chars[i], i+1)>0) {
                return PassRuleResult.builder()
                        .valid(false)
                        .message(rejectMessage)
                        .build();
            }
        }
        return PassRuleResult.builder().valid(true).build();
    }
}
