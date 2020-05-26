package hmo.passcheck.domain.mapper;

import hmo.passcheck.domain.dto.RuleDto;
import hmo.passcheck.repository.entity.Rule;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RuleMapper {

    RuleDto getRuleDto(Rule rule);

    List<RuleDto> getRuleDtoList(List<Rule> rules);
}
