package innometrics.sonarqube;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URL;

@SpringBootApplication
public class SonarqubeApplication {
    @Autowired
MetricsFetcher metricsFetcher;
    public static void main(String[] args) throws IOException, JSONException {

        SpringApplication.run(SonarqubeApplication.class, args);
    }
}
