package innometrics.sonarqube.services;

import innometrics.sonarqube.repositories.ReliabilityRepository;
import innometrics.sonarqube.repositories.SizeAndComplexityRepository;
import innometrics.sonarqube.tables.Reliability;
import innometrics.sonarqube.tables.Sizeandcomplexity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeAndComplexityService {
    @Autowired
    private SizeAndComplexityRepository sizeAndComplexityRepository;

    public void createReliability(Sizeandcomplexity sizeandcomplexity){
        sizeAndComplexityRepository.save(sizeandcomplexity);
    }
    public void deleteReliabilities(){
        sizeAndComplexityRepository.deleteAll();
    }
}
