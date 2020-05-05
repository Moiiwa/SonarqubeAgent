package innometrics.sonarqube.services;

import innometrics.sonarqube.repositories.ReliabilityRepository;
import innometrics.sonarqube.tables.Maintainability;
import innometrics.sonarqube.tables.Reliability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReliabilityService {
    @Autowired
    private ReliabilityRepository reliabilityRepository;

    public void createReliability(Reliability reliability){
        reliabilityRepository.save(reliability);
    }
    public void deleteReliabilities(){
        reliabilityRepository.deleteAll();
    }
}
