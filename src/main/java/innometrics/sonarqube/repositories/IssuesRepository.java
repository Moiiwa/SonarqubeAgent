package innometrics.sonarqube.repositories;

import innometrics.sonarqube.tables.Issues;
import innometrics.sonarqube.tables.Maintainability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface IssuesRepository extends JpaRepository<Issues,Long> {
    @Query(value = "select * from issues", nativeQuery = true)
    List<Issues> findAllIssues();
}
