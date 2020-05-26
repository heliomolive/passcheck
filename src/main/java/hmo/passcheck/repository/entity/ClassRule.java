package hmo.passcheck.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
public class ClassRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLASS_RULE_ID")
    private Long classRuleId;

    @ManyToOne
    @JoinColumn(name = "PASS_CLASS_ID", nullable = false)
    private PassClass passClass;

    @ManyToOne
    @JoinColumn(name = "RULE_ID", nullable = false)
    private Rule rule;

    @Column(name = "RULE_VALUE")
    private String ruleValue;

    @Column(name = "RULE_ORDER", nullable = false)
    private Integer ruleOrder;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDateTime createDate;
}
