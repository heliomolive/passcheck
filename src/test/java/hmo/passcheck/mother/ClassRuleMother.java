package hmo.passcheck.mother;

import hmo.passcheck.repository.entity.ClassRule;
import hmo.passcheck.repository.entity.PassClass;
import hmo.passcheck.repository.entity.Rule;

import java.time.LocalDateTime;

public class ClassRuleMother {

    private ClassRuleMother() {}

    public static ClassRule getClassRule(Long classRuleId, PassClass passClass, Rule rule, String ruleValue) {
        return getClassRule(classRuleId, passClass, rule, ruleValue, 1);
    }

    public static ClassRule getClassRule(Long classRuleId, PassClass passClass, Rule rule, String ruleValue, Integer ruleOrder) {
        return ClassRule.builder()
                .classRuleId(classRuleId)
                .passClass(passClass)
                .rule(rule)
                .ruleOrder(ruleOrder)
                .ruleValue(ruleValue)
                .createDate(LocalDateTime.now())
                .build();
    }
}
