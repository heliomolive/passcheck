package hmo.passcheck.domain.mapper;

import hmo.passcheck.domain.dto.PassClassDto;
import hmo.passcheck.repository.entity.PassClass;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PassClassMapper {

    PassClassDto getPassClassDto(PassClass passClass);
}
