package hmo.passcheck.mother;

import hmo.passcheck.domain.enums.RuleValueType;
import hmo.passcheck.repository.entity.Rule;

import java.time.LocalDateTime;

import static hmo.passcheck.domain.enums.RuleName.MIN_PASS_SIZE;
import static hmo.passcheck.domain.enums.RuleName.MAX_PASS_SIZE;

public class RuleMother {

    private RuleMother() {}

    public static Rule getRuleForMinimumSize(Long ruleId) {
        return Rule.builder()
                .ruleId(ruleId)
                .ruleName(MIN_PASS_SIZE)
                .ruleDesc(MIN_PASS_SIZE.getDescription())
                .ruleValueType(RuleValueType.INTEGER)
                .rejectMessage("Tamanho mínimo da senha: %s caracteres")
                .createDate(LocalDateTime.now())
                .build();
    }

    public static Rule getRuleForMaximumSize(Long ruleId) {
        return Rule.builder()
                .ruleId(ruleId)
                .ruleName(MAX_PASS_SIZE)
                .ruleDesc(MAX_PASS_SIZE.getDescription())
                .ruleValueType(RuleValueType.INTEGER)
                .rejectMessage("Tamanho máximo da senha: %s caracteres")
                .createDate(LocalDateTime.now())
                .build();
    }
}
