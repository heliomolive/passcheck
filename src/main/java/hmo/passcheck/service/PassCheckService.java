package hmo.passcheck.service;

import hmo.passcheck.domain.dto.ClassRuleDto;
import hmo.passcheck.domain.dto.PassClassDto;
import hmo.passcheck.domain.exception.UnprocessableEntityException;
import hmo.passcheck.service.rule.PassRule;
import hmo.passcheck.service.rule.PassRuleFactory;
import hmo.passcheck.service.rule.PassRuleResult;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Log4j2
@Setter
@Service
public class PassCheckService {

    @Autowired
    private PassClassService passClassService;

    @Value("${passcheck.invalid.password}")
    private String invalidPasswordUserMsg;


    public void validate(String password, String passClassAcronym) {
        PassClassDto passClassDto = passClassService.findPassClass(passClassAcronym);

        validate(password, passClassDto);
    }

    private void validate(String password, PassClassDto passClassDto) {
        List<String> errors = new ArrayList<>();

        for (ClassRuleDto classRuleDto : passClassDto.getRules()) {
            // Get the implementation class for this rule:
            PassRule passRuleImp = PassRuleFactory.getPassRule(classRuleDto.getRule().getRuleName(),
                    classRuleDto.getRule().getRejectMessage(), classRuleDto.getRuleValue());

            // Apply the rule for this password:
            PassRuleResult passRuleResult = passRuleImp.validate(password, classRuleDto.getRuleValue());

            if (!passRuleResult.getValid()) {
                errors.add(passRuleResult.getMessage());
            }
        }

        if (!errors.isEmpty()) {
            String ruleMessages = errors.toString();
            log.debug("Password [{}] rejected. {}", password, ruleMessages);
            throw UnprocessableEntityException.builder()
                    .developerMessage("Invalid password format.")
                    .userMessage(format(invalidPasswordUserMsg, ruleMessages))
                    .build();
        } else {
            log.debug("Password [{}] valid!", password);
        }
    }

}
