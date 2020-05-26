package hmo.passcheck.repository.entity;

import hmo.passcheck.domain.enums.RuleName;
import hmo.passcheck.domain.enums.RuleValueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
public class Rule {

    @Id
    @Column(name = "RULE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ruleId;

    @Column(name = "RULE_NAME", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RuleName ruleName;

    @Column(name = "RULE_DESC")
    private String ruleDesc;

    @Column(name = "RULE_VALUE_TYPE")
    @Enumerated(value = EnumType.STRING)
    private RuleValueType ruleValueType;

    @Column(name = "REJECT_MESSAGE", nullable = false)
    private String rejectMessage;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDateTime createDate;
}
