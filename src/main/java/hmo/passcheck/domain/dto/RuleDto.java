package hmo.passcheck.domain.dto;

import hmo.passcheck.domain.enums.RuleName;
import hmo.passcheck.domain.enums.RuleValueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDto {

    private Long ruleId;
    private RuleName ruleName;
    private String ruleDesc;
    private RuleValueType ruleValueType;
    private String rejectMessage;
}
