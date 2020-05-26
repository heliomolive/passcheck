package hmo.passcheck.domain.mapper;

import hmo.passcheck.domain.dto.ClassRuleDto;
import hmo.passcheck.repository.entity.ClassRule;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassRuleMapper {

    ClassRuleDto getClassRuleDto(ClassRule classRule);

    List<ClassRuleDto> getClassRuleDtoList(List<ClassRule> rules);
}
