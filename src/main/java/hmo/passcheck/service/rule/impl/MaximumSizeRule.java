package hmo.passcheck.service.rule.impl;

import hmo.passcheck.service.rule.PassRule;
import hmo.passcheck.service.rule.PassRuleResult;

import static hmo.passcheck.service.rule.RuleValueParser.parseIntRuleValue;
import static java.lang.String.format;

public class MaximumSizeRule implements PassRule {

    private String rejectMessage;

    public MaximumSizeRule(String errorMessage, Object... messageParams) {
        rejectMessage = format(errorMessage, messageParams);
    }

    @Override
    public <T> PassRuleResult validate(String password, T ruleValue) {
        if (password.length() > parseIntRuleValue(ruleValue)) {
            return PassRuleResult.builder()
                    .valid(false)
                    .message(rejectMessage)
                    .build();
        }
        return PassRuleResult.builder().valid(true).build();
    }
}
