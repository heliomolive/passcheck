package hmo.passcheck.repository;

import hmo.passcheck.domain.enums.PassClassName;
import hmo.passcheck.mother.PassClassMother;
import hmo.passcheck.repository.entity.PassClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PassClassRepositoryTest {

    @Autowired
    private PassClassRepository fixture;

    @Test
    public void savePassClass() {
        PassClass saved = findOrCreate();

        Optional<PassClass> found = fixture.findById(saved.getPassClassId());
        assertTrue(found.isPresent());
        assertEquals(saved, found.get());
    }

    @Test
    public void findByPassClassName() {
        PassClass passClass = findOrCreate();

        Optional<PassClass> found = fixture.findByPassClassName(passClass.getPassClassName());

        assertTrue(found.isPresent());
        assertEquals(passClass, found.get());
    }

    private PassClass findOrCreate() {
        Optional<PassClass> passClass = fixture.findOne(Example.of(
                PassClass.builder().passClassName(PassClassName.NCPWR).build()));
        return passClass.orElseGet(() -> fixture.saveAndFlush(PassClassMother.getPassClass(null)));
    }

}
