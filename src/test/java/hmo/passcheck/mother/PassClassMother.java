package hmo.passcheck.mother;

import hmo.passcheck.repository.entity.ClassRule;
import hmo.passcheck.repository.entity.PassClass;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static hmo.passcheck.domain.enums.PassClassName.NCPWR;

public class PassClassMother {

    private PassClassMother() {}

    public static PassClass getPassClass(Long passClassId) {
        return getPassClass(passClassId, new ArrayList<>());
    }

    public static PassClass getPassClass(Long passClassId, List<ClassRule> classRules) {
        return PassClass.builder()
                .passClassId(passClassId)
                .passClassName(NCPWR)
                .passClassDesc(NCPWR.getDescription())
                .createDate(LocalDateTime.now())
                .rules(classRules)
                .build();
    }
}
