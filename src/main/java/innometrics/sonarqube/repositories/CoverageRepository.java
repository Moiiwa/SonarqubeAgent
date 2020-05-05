package innometrics.sonarqube.repositories;

import innometrics.sonarqube.tables.Coverage;
import innometrics.sonarqube.tables.Sizeandcomplexity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CoverageRepository extends JpaRepository<Coverage,Long> {
    @Query(value = "select * from coverage", nativeQuery = true)
    List<Coverage> findAllCoverage();
}
