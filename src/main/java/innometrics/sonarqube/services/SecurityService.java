package innometrics.sonarqube.services;

import innometrics.sonarqube.repositories.CoverageRepository;
import innometrics.sonarqube.repositories.SecurityRepository;
import innometrics.sonarqube.tables.Coverage;
import innometrics.sonarqube.tables.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    private SecurityRepository securityRepository;
    public void createSecurity(Security security){
        securityRepository.save(security);
    }
    public void deleteSecurity(){
        securityRepository.deleteAll();
    }
}
