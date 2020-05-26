package hmo.passcheck.repository;

import hmo.passcheck.repository.entity.ClassRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRuleRepository extends JpaRepository<ClassRule, Long> {
}
