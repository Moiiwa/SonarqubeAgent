package innometrics.sonarqube.repositories;

import innometrics.sonarqube.tables.Security;
import innometrics.sonarqube.tables.Sizeandcomplexity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SecurityRepository extends JpaRepository<Security,Long> {
    @Query(value = "select * from security", nativeQuery = true)
    List<Security> findAllSecurity();
}
