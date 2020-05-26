package hmo.passcheck.service.rule;

public interface PassRule {

    <T> PassRuleResult validate(String password, T ruleValue);
}