package innometrics.sonarqube;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;
import java.net.http.HttpRequest;

public class MetricsFetcher {
    public void fetch(URL url, String ... params) throws IOException, JSONException {
        URL newUrl=new URL(concatenateParams(url,params));
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
        System.out.println(measures.get(0));
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
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
