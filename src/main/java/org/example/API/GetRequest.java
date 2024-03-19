package org.example.API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest {
    private final String getUrl;

    public GetRequest(String getUrl) {
        this.getUrl = getUrl;
    }

    public String execute() {
        try {
            // Create URL and create http object
            URL url = new URL(this.getUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set method
            connection.setRequestMethod("GET");

            // Bad request/no response
            int responseCode = connection.getResponseCode();
            if (responseCode < 200 || responseCode > 299) {
                return "Bad request/no response: " + responseCode;
            }

            // Read response body
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Disconnect
            connection.disconnect();

            // Provide response
            return response.toString();
        } catch (Exception e) {
//            e.printStackTrace();
            return "Could not reach the server!";
        }
    }
}
