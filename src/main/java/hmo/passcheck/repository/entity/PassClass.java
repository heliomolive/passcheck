package hmo.passcheck.repository.entity;

import hmo.passcheck.domain.enums.PassClassName;
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
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
public class PassClass {

    @Id
    @Column(name = "PASS_CLASS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passClassId;

    @Column(name = "PASS_CLASS_NAME", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PassClassName passClassName;

    @Column(name = "PASS_CLASS_DESC")
    private String passClassDesc;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "passClass")
    private List<ClassRule> rules;
}
