package innometrics.sonarqube.controllers;

import innometrics.sonarqube.MetricsFetcher;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    MetricsFetcher metricsFetcher;

    @PostMapping("/")
    public void fetch() throws IOException, JSONException {
        metricsFetcher.fetch(new URL("http://localhost:9000/api/measures/component"),"component=digital_library","login=admin","password=admin",
                "metricKeys=new_technical_debt,blocker_violations,bugs,classes," +
                        "code_smells,complexity_in_classes,branch_coverage,coverage,violations,line_coverage,lines,ncloc," +
                        "sqale_rating,major_violations,minor_violations,new_blocker_violations,new_bugs,new_code_smells," +
                        "new_critical_violations,new_major_violations,new_minor_violations,new_security_hotspots," +
                        "new_vulnerabilities,alert_status,reliability_rating,reopened_issues,security_hotspots,new_security_hotspots_reviewed,security_rating");
    }
}
