package hmo.passcheck.domain.dto;

import hmo.passcheck.domain.enums.PassClassName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassClassDto {

    private Long passClassId;
    private PassClassName passClassName;
    private String passClassDesc;
    private List<ClassRuleDto> rules;
}
