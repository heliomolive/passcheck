package hmo.passcheck.controller;

import hmo.passcheck.domain.mapper.ClassRuleMapper;
import hmo.passcheck.domain.mapper.PassClassMapper;
import hmo.passcheck.domain.mapper.RuleMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerTestConfig {

    @Bean
    @ConditionalOnMissingBean
    public ClassRuleMapper getClassRuleMapper() {
        return Mappers.getMapper(ClassRuleMapper.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public PassClassMapper getPassClassMapper() {
        return Mappers.getMapper(PassClassMapper.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public RuleMapper getRuleMapper() {
        return Mappers.getMapper(RuleMapper.class);
    }
}