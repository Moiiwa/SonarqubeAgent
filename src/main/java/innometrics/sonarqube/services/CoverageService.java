package innometrics.sonarqube.services;

import innometrics.sonarqube.repositories.CoverageRepository;
import innometrics.sonarqube.tables.Coverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverageService {
    @Autowired
    private CoverageRepository coverageRepository;
    public void createCoverage(Coverage coverage){
        coverageRepository.save(coverage);
    }
    public void deleteCoverage(){
        coverageRepository.deleteAll();
    }
}
