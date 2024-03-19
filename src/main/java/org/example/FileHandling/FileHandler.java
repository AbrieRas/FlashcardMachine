package org.example.FileHandling;

// Libraries
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileHandler {
    private final String fileUrl;
    private String apiKey;
    private String apiSecret;
    private String apiLockKey;
    private String serverUrl;

    public FileHandler(String fileUrl) {
        this.fileUrl = fileUrl;
        this.loadEnvFile();
    }

    public String getApiKey() {
        // key is not present
        if (this.apiKey == null) {
            return "API key not found in .env file: " + this.fileUrl;
        }

        return this.apiKey;
    }

    public String getApiSecret() {
        // secret is not present
        if (this.apiSecret == null) {
            return "API secret not found in .env file: " + this.fileUrl;
        }

        return this.apiSecret;
    }

    public String getApiLockKey() {
        // lock key is not present
        if (this.apiLockKey == null) {
            return "API lock key not found in .env file: " + this.fileUrl;
        }

        return this.apiLockKey;
    }

    public String getServerUrl() {
        // server url is not present
        if (this.serverUrl == null) {
            return "Server URL not found in .env file: " + this.fileUrl;
        }

        return this.serverUrl;
    }

    private void loadEnvFile() {
        // Load the .env file
        Properties properties = new Properties();
        try {
            FileInputStream envFile = new FileInputStream(this.fileUrl);
            properties.load(envFile);
            envFile.close();
        } catch (IOException e) {
            return;
        }

        // Access properties from .env file
        String apiKey = properties.getProperty("API_KEY");
        String apiSecret = properties.getProperty("API_SECRET");
        String apiLockKey = properties.getProperty("API_LOCK_KEY");
        String serverUrl = properties.getProperty("SERVER_URL");
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.apiLockKey = apiLockKey;
        this.serverUrl = serverUrl;
    }
}
