package innometrics.sonarqube;

import innometrics.sonarqube.services.*;
import innometrics.sonarqube.tables.*;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.net.*;

import org.json.*;

import org.apache.commons.codec.binary.Base64;
import org.sonar.api.SonarPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
@Service
public class MetricsFetcher {

    @Autowired
    MaintainabilityService maintainabilityService;

    @Autowired
    IssuesService issuesService;

    @Autowired
    ReliabilityService reliabilityService;

    @Autowired
    SizeAndComplexityService sizeAndComplexityService;

    @Autowired
    CoverageService coverageService;

    @Autowired
    SecurityService securityService;

    public void createHook(URL url, String componentName) throws IOException {
        Authenticator.setDefault(new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("admin","admin".toCharArray());
            }
        });
        String ip;
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));
        ip=in.readLine();
        //ip="52.29.65.138";
        URL newUrl=new URL(url+"?project="+componentName+"&url=http://"+ip+":9096/webhook&name=InnoMetricsWebHook");
        //URL newUrl=new URL("http://localhost:9000/api/webhooks/create?project=digital_library&url=http://52.29.65.138:9095/webhook&name=InnoMetricsWebHook");
        HttpURLConnection connection=(HttpURLConnection)newUrl.openConnection();
        String userPassword = "admin:admin";

        connection.setRequestProperty("Authorization","Basic "+ new String(Base64.encodeBase64(userPassword.getBytes())));
        connection.setRequestMethod("POST");
        connection.setAuthenticator(Authenticator.getDefault());
        connection.setRequestProperty("Content-Type","JSON");
        connection.setRequestProperty("Content-Language", "en-US");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        //outputStream.writeBytes("login=admin&password=admin");
        outputStream.writeBytes("");
        outputStream.close();

        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        System.out.println(response.toString());
    }

    public void fetch(URL url, String ... params) throws IOException, JSONException {
        URL newUrl=new URL(concatenateParams(url,params));
        System.out.println(newUrl);
        HttpURLConnection connection=(HttpURLConnection) newUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type","JSON");
        connection.setRequestProperty("Content-Language", "en-US");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        //outputStream.writeBytes("login=admin&password=admin");
        outputStream.writeBytes("");
        outputStream.close();

        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        JSONObject object = new JSONObject(String.valueOf(response));
        JSONObject component= (JSONObject) object.get("component");
        JSONArray measures= (JSONArray) component.get("measures");
        addMaintainability(measures);
        addIssues(measures);
        addReliability(measures);
        addSizeAndComplexity(measures);
        addCoverage(measures);
        addSecurity(measures);
    }
    public void addSecurity(JSONArray measures) throws JSONException{
        Security security = new Security();
        for (int i=0;i<measures.length();i++) {
            JSONObject measure = measures.getJSONObject(i);
            if(measure.get("metric").equals("security_hotspots")) {
                security.setSecurityHotspots((String) measure.get("value"));
            }
            if(measure.get("metric").equals("security_rating")) {
                security.setSecurityRating((String) measure.get("value"));
            }
            if(measure.get("metric").equals("new_security_hotspots")) {
                JSONObject period=measure.getJSONObject("period");
                security.setNewSecurityHotspots((String) period.get("value"));
            }
            if(measure.get("metric").equals("new_security_hotspots_reviewed")) {
                JSONObject period=measure.getJSONObject("period");
                security.setNewSecurityHotspotsReviewed((String) period.get("value"));
            }
            if(measure.get("metric").equals("new_vulnerabilities")) {
                JSONObject period=measure.getJSONObject("period");
                security.setNewVulnerabilities((String) period.get("value"));
            }
        }
        securityService.createSecurity(security);
    }
    public void addSizeAndComplexity(JSONArray measures) throws JSONException{
        Sizeandcomplexity sizeandcomplexity = new Sizeandcomplexity();
        for (int i=0;i<measures.length();i++) {
            JSONObject measure = measures.getJSONObject(i);
            if(measure.get("metric").equals("classes")) {
                sizeandcomplexity.setClasses((String) measure.get("value"));
            }
            if(measure.get("metric").equals("lines")) {
                sizeandcomplexity.setLines((String) measure.get("value"));
            }
            if(measure.get("metric").equals("ncloc")) {
                sizeandcomplexity.setNcloc((String) measure.get("value"));
            }
            if(measure.get("metric").equals("complexity_in_classes")) {
                sizeandcomplexity.setComplexityInClasses((String) measure.get("value"));
            }
        }
        sizeAndComplexityService.createReliability(sizeandcomplexity);
    }
    public void addCoverage(JSONArray measures) throws JSONException{
        Coverage coverage = new Coverage();
        for (int i=0;i<measures.length();i++) {
            JSONObject measure = measures.getJSONObject(i);
            if(measure.get("metric").equals("branch_coverage")) {
                coverage.setBranchCoverage((String) measure.get("value"));
            }
            if(measure.get("metric").equals("coverage")) {
                coverage.setCoverage((String) measure.get("value"));
            }
            if(measure.get("metric").equals("line_coverage")) {
                coverage.setLineCoverage((String) measure.get("value"));
            }
        }
        coverageService.createCoverage(coverage);
    }
    public void addReliability(JSONArray measures) throws JSONException{
        Reliability reliability = new Reliability();
        for (int i=0;i<measures.length();i++) {
            JSONObject measure = measures.getJSONObject(i);
            if(measure.get("metric").equals("bugs")) {
                reliability.setBugs((String) measure.get("value"));
            }
            if(measure.get("metric").equals("alert_status")) {
                reliability.setAlertStatus((String) measure.get("value"));
            }
            if(measure.get("metric").equals("reliability_rating")) {
                reliability.setReliabilityRating((String) measure.get("value"));
            }
            if(measure.get("metric").equals("new_bugs")) {
                JSONObject period=measure.getJSONObject("period");
                reliability.setNewBugs((String) period.get("value"));
            }
        }
        reliabilityService.createReliability(reliability);
    }
    public void addIssues(JSONArray measures) throws JSONException {
        Issues issues = new Issues();
        for (int i=0;i<measures.length();i++){
            JSONObject measure=measures.getJSONObject(i);
            if(measure.get("metric").equals("blocker_violations")) {
                issues.setBlockerViolations((String) measure.get("value"));
            }
            if(measure.get("metric").equals("violations")) {
                issues.setViolations((String) measure.get("value"));
            }
            if(measure.get("metric").equals("major_violations")) {
                issues.setMajorViolations((String) measure.get("value"));
            }
            if(measure.get("metric").equals("minor_violations")) {
                issues.setMinorViolations((String) measure.get("value"));
            }
            if(measure.get("metric").equals("reopened_issues")) {
                issues.setReopenedIssues((String) measure.get("value"));
            }
            if(measure.get("metric").equals("new_blocker_violations")) {
                JSONObject period=measure.getJSONObject("period");
                issues.setNewBlockerViolations((String) period.get("value"));
            }
            if(measure.get("metric").equals("new_critical_violations")) {
                JSONObject period=measure.getJSONObject("period");
                issues.setNewCriticalViolations((String) period.get("value"));
            }
            if(measure.get("metric").equals("new_major_violations")) {
                JSONObject period=measure.getJSONObject("period");
                issues.setNewMajorViolations((String) period.get("value"));
            }
            if(measure.get("metric").equals("new_minor_violations")) {
                JSONObject period=measure.getJSONObject("period");
                issues.setNewMinorViolations((String) period.get("value"));
            }
        }
        issuesService.createIssues(issues);
    }
    public void addMaintainability(JSONArray measures) throws JSONException {
        Maintainability maintainability=new Maintainability();
        for (int i=0;i<measures.length();i++){
            JSONObject measure=measures.getJSONObject(i);
            if(measure.get("metric").equals("new_technical_debt")) {
                JSONObject period=measure.getJSONObject("period");
                maintainability.setNew_technical_debt((String) period.get("value"));
            }
            if(measure.get("metric").equals("code_smells"))
                maintainability.setCode_smells((String) measure.get("value"));
            if(measure.get("metric").equals("sqale_rating"))
                maintainability.setSqale_rating((String) measure.get("value"));
            if(measure.get("metric").equals("new_code_smells")) {
                JSONObject period=measure.getJSONObject("period");
                maintainability.setNew_code_smells((String) period.get("value"));
            }
        }
        maintainabilityService.createMaintainability(maintainability);
    }


    public String concatenateParams(URL url,String ... params){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(url);
        stringBuilder.append("?");
        for(String param:params){
            stringBuilder.append(param);
            stringBuilder.append("&");
        }
        stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
        return stringBuilder.toString();
    }
}
