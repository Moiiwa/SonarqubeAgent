package innometrics.sonarqube.controllers;

import innometrics.sonarqube.MetricsFetcher;
import innometrics.sonarqube.SonarqubeApplication;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class WebHook {
    @Autowired
    RestController restController;
    @Autowired
    MetricsFetcher metricsFetcher;

    private static Logger LOG;

    @PostMapping("/webhook")
    public RedirectView webhook() throws IOException, JSONException {
        metricsFetcher.fetch(new URL("http://"+restController.urlString+"/api/measures/component"),"component="+restController.componentName,"login=admin","password=admin",
                "metricKeys=new_technical_debt,blocker_violations,bugs,classes," +
                        "code_smells,complexity_in_classes,branch_coverage,coverage,violations,line_coverage,lines,ncloc," +
                        "sqale_rating,major_violations,minor_violations,new_blocker_violations,new_bugs,new_code_smells," +
                        "new_critical_violations,new_major_violations,new_minor_violations,new_security_hotspots," +
                        "new_vulnerabilities,alert_status,reliability_rating,reopened_issues,security_hotspots,new_security_hotspots_reviewed,security_rating");
        LOG=LogManager.getLogger(SonarqubeApplication.class);
        LOG.warn("Data is updated");
        return new RedirectView("/ref");
    }
}
