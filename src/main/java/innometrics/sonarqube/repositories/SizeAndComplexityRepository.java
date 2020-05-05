package innometrics.sonarqube.repositories;

import innometrics.sonarqube.tables.Reliability;
import innometrics.sonarqube.tables.Sizeandcomplexity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SizeAndComplexityRepository extends JpaRepository<Sizeandcomplexity,Long> {
    @Query(value = "select * from sizeandcomplexity", nativeQuery = true)
    List<Sizeandcomplexity> findAllSizeAndComplexity();
}
