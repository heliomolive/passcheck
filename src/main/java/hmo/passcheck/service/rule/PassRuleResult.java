package hmo.passcheck.service.rule;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassRuleResult {

    private Boolean valid;
    private String message;
}
