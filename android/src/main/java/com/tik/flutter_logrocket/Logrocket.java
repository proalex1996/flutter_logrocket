package com.tik.flutter_logrocket;

import android.app.Application;
import android.content.Context;
import com.logrocket.core.SDK;
import com.logrocket.core.network.IResponseBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class Logrocket extends Application {
    protected String appId;
    protected String URL;
    @Override
    protected void attachBaseContext(Context base) {
      
        SDK.init(
                this,
                base,
                options -> {
                    options.setAppID(appId);
                }
        );
        super.attachBaseContext(base);
    }

//    protected void identifyUser(String userId){
//     return SDK.identify(userId);
//    }

   protected void sendGetRequest() throws IOException{
    URL endpoint = new URL(URL);
    IResponseBuilder responseBuilder =
    SDK.newRequestBuilder()
        .setUrl(endpoint.toString())
        .setMethod("GET")
        .capture();
    long startTime = System.currentTimeMillis();
    try {
        HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
  
        responseBuilder
            .setStatusCode(conn.getResponseCode())
            .setDuration(System.currentTimeMillis() - startTime)
            .setBody(readRedactedResponseBody(conn))
            .setHeaders(flattenAndRedactHeaders(conn.getHeaderFields()))
            .capture();
      } catch (IOException err) {
  
        responseBuilder
            .setStatusCode(0)
            .setDuration(System.currentTimeMillis() - startTime)
            .capture();
        throw err;
      }
   }

   private String readRedactedResponseBody(HttpURLConnection conn) throws IOException {
    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

      return redactBody(response.toString());
    }

    return "";
  }

  private String redactBody(String body) {
    if (body.contains("ignore")) {
      return "";
    }
    return body;
  }

    private Map<String, String> flattenAndRedactHeaders(Map<String,List<String>> headers) {
        Map<String, String> result = new HashMap<>();

        for (Entry<String, List<String>> entry : headers.entrySet()) {
        String key = entry.getKey();

        // Do not capture auth related headers.
        if (key.toLowerCase().equals("authentication") || key.toLowerCase().equals("authorization")) {
            continue;
        }

        List<String> values = entry.getValue();
        result.put(key, joinValues(values));
        }

        return result;
    }
    private String joinValues(List<String> values) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.size(); i++) {
        sb.append(values.get(i));
        if (i != values.size() - 1) {
            sb.append(",");
        }
        }

        return sb.toString();
    }
}
