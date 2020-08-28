package hmo.passcheck.service;

import hmo.passcheck.domain.dto.ClassRuleDto;
import hmo.passcheck.domain.dto.PassClassDto;
import hmo.passcheck.domain.exception.BadRequestException;
import hmo.passcheck.domain.mapper.PassClassMapper;
import hmo.passcheck.repository.PassClassRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import static java.lang.String.format;

@Setter
@Service
public class PassClassService {

    @Autowired
    private PassClassRepository passClassRepository;

    @Autowired
    private PassClassMapper passClassMapper;

    @Cacheable(cacheNames = "passClassCache", key="#passClassName")
    public PassClassDto findPassClass(String passClassName) {
        PassClassDto passClassDto = passClassRepository
                .findByPassClassName(passClassName)
                .map( passClass -> passClassMapper.getPassClassDto(passClass) )
                .orElseThrow(
                        () -> BadRequestException.builder()
                                .developerMessage(format("Invalid Pass Class Acronym [%s]", passClassName))
                                .build() );

        passClassDto.getRules().sort(
                Comparator.comparing(ClassRuleDto::getRuleOrder) );

        return passClassDto;
    }
}
