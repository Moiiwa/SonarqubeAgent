package innometrics.sonarqube.controllers;

import innometrics.sonarqube.MetricsFetcher;
import innometrics.sonarqube.repositories.*;
import innometrics.sonarqube.services.CoverageService;
import innometrics.sonarqube.tables.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    MetricsFetcher metricsFetcher;

    @Autowired
    CoverageRepository coverageRepository;

    @Autowired
    IssuesRepository issuesRepository;

    @Autowired
    MaintainabilityRepository maintainabilityRepository;

    @Autowired
    ReliabilityRepository reliabilityRepository;

    @Autowired
    SecurityRepository securityRepository;

    @Autowired
    SizeAndComplexityRepository sizeAndComplexityRepository;

    public String componentName;

    public String urlString;

    @PostMapping("/")
    public void fetch(@RequestParam(name = "host_and_port") String urlString,@RequestParam(name = "component_name") String componentName) throws IOException, JSONException {
        this.componentName=componentName;
        this.urlString=urlString;
        metricsFetcher.fetch(new URL("http://"+urlString+"/api/measures/component"),"component="+componentName,"login=admin","password=admin",
                "metricKeys=new_technical_debt,blocker_violations,bugs,classes," +
                        "code_smells,complexity_in_classes,branch_coverage,coverage,violations,line_coverage,lines,ncloc," +
                        "sqale_rating,major_violations,minor_violations,new_blocker_violations,new_bugs,new_code_smells," +
                        "new_critical_violations,new_major_violations,new_minor_violations,new_security_hotspots," +
                        "new_vulnerabilities,alert_status,reliability_rating,reopened_issues,security_hotspots,new_security_hotspots_reviewed,security_rating");
        metricsFetcher.createHook(new URL("http://"+urlString+"/api/webhooks/create"),componentName);
    }
    @PostMapping("/ref")
    public String ref(){
        return "Scan successfully finished";
    }
    @GetMapping("/coverage")
    public List<Coverage> allCoverage(){
        return coverageRepository.findAllCoverage();
    }

    @GetMapping("/issues")
    public List<Issues> allIssues(){
        return issuesRepository.findAllIssues();
    }

    @GetMapping("/maintainability")
    public List<Maintainability> allMaintainability(){
        return maintainabilityRepository.findAllMaintainabilities();
    }

    @GetMapping("/reliability")
    public List<Reliability> allReliability(){
        return reliabilityRepository.findAllReliabilities();
    }

    @GetMapping("/security")
    public List<Security> allSecurity(){
        return securityRepository.findAllSecurity();
    }

    @GetMapping("/sizeandcomplexity")
    public List<Sizeandcomplexity> allSAC(){
        return sizeAndComplexityRepository.findAllSizeAndComplexity();
    }
}
