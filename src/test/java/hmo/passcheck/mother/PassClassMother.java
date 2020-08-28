package hmo.passcheck.mother;

import hmo.passcheck.repository.entity.ClassRule;
import hmo.passcheck.repository.entity.PassClass;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PassClassMother {

    private PassClassMother() {}

    public static PassClass getPassClass(Long passClassId) {
        return getPassClass(passClassId, new ArrayList<>());
    }

    public static PassClass getPassClass(Long passClassId, List<ClassRule> classRules) {
        return PassClass.builder()
                .passClassId(passClassId)
                .passClassName("NCPWR")
                .passClassDesc("Nine Chars Password Without Repetition")
                .createDate(LocalDateTime.now())
                .rules(classRules)
                .build();
    }
}
