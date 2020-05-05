package innometrics.sonarqube.services;

import innometrics.sonarqube.repositories.IssuesRepository;
import innometrics.sonarqube.tables.Issues;
import innometrics.sonarqube.tables.Maintainability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuesService {
    @Autowired
    private IssuesRepository issuesRepository;
    public void createIssues(Issues issues){
        issuesRepository.save(issues);
    }
    public void deleteIssues(){
        issuesRepository.deleteAll();
    }
}
