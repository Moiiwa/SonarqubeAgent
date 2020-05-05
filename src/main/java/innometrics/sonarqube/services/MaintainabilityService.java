package innometrics.sonarqube.services;

import innometrics.sonarqube.repositories.MaintainabilityRepository;
import innometrics.sonarqube.tables.Maintainability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintainabilityService {
    @Autowired
    private MaintainabilityRepository maintainabilityRepository;

    public void createMaintainability(Maintainability maintainability){
        maintainabilityRepository.save(maintainability);
    }
    public void deleteMaintainabilities(){
        maintainabilityRepository.deleteAll();
    }
}
