package innometrics.sonarqube.repositories;

import innometrics.sonarqube.tables.Maintainability;
import innometrics.sonarqube.tables.Reliability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReliabilityRepository extends JpaRepository<Reliability,Long> {
    @Query(value = "select * from reliability", nativeQuery = true)
    List<Reliability> findAllReliabilities();
}
