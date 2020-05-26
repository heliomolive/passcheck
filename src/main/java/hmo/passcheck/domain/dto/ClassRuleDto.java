package hmo.passcheck.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassRuleDto {

    private Long classRuleId;
    private RuleDto rule;
    private String ruleValue;
    private Integer ruleOrder;
}
