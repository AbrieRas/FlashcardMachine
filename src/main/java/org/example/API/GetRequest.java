package org.example.API;

// Classes
import org.example.FileHandling.FileHandler;
import org.example.Resources.Encryption;

// Libraries
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest {
    private final String getUrl;
    private final String apiKey = new FileHandler(getEnvPath()).getApiKey();
    private final String apiSecret = new FileHandler(getEnvPath()).getApiSecret();
    private final String secretKey = new FileHandler(getEnvPath()).getApiLockKey();

    public GetRequest(String getUrl) {
        this.getUrl = getUrl;
    }

    public String execute() {
        try {
            // Initialize encryption environment
            var encryption = new Encryption();

            // Hash API key and secret using SHA-256
            String hashedApiKey = encryption.hashString(this.apiKey);
            String hashedApiSecret = encryption.hashString(this.apiSecret);

            // Create URL and create http object
            URL url = new URL(this.getUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Create JWT token and set header & method
            String jwtToken = encryption.createJwtToken(hashedApiKey, hashedApiSecret, this.secretKey);
            connection.setRequestProperty("Authorization", "Bearer " + jwtToken);
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
            return "Could not reach the server!";
        }
    }

    private String getEnvPath() {
        // current directory and path to .env file
        String currentDirectory = System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "main"
                + File.separator + "java"
                + File.separator + "org"
                + File.separator + "example"
                + File.separator;
        String envFilePath = "Resources"
                + File.separator + ".env";

        return currentDirectory + envFilePath;
    }
}
