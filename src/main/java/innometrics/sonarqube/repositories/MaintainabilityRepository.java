package innometrics.sonarqube.repositories;

import innometrics.sonarqube.tables.Maintainability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MaintainabilityRepository extends JpaRepository<Maintainability,Long> {
    @Query(value = "select * from maintainability", nativeQuery = true)
    List<Maintainability> findAllMaintainabilities();
}
