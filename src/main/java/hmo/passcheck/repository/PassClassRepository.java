package hmo.passcheck.repository;

import hmo.passcheck.repository.entity.PassClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassClassRepository extends JpaRepository<PassClass, Long> {

    Optional<PassClass> findByPassClassName(String passClassName);
}
