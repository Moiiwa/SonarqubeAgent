package innometrics.sonarqube;

import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URL;

@SpringBootApplication
public class SonarqubeApplication {

    public static void main(String[] args) throws IOException, JSONException {
        MetricsFetcher metricsFetcher=new MetricsFetcher();
        metricsFetcher.fetch(new URL("http://localhost:9000/api/measures/component"),"component=digital_library","login=admin","password=admin",
                "metricKeys=new_technical_debt,blocker_violations,bugs,classes," +
                        "code_smells,complexity_in_classes,branch_coverage,coverage,violations,line_coverage,lines,ncloc," +
                        "sqale_rating,major_violations,minor_violations,new_blocker_violations,new_bugs,new_code_smells," +
                        "new_critical_violations,new_major_violations,new_minor_violations,new_security_hotspots," +
                        "new_vulnerabilities,alert_status,reliability_rating,reopened_issues,security_hotspots,new_security_hotspots_reviewed,security_rating");
        SpringApplication.run(SonarqubeApplication.class, args);
    }

}
