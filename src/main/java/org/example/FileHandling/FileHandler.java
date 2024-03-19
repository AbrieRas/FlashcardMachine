package org.example.FileHandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileHandler {
    private final String fileUrl;
    private String apiKey;
    private String apiSecret;

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

    private void loadEnvFile() {
        // Load the .env file
        Properties properties = new Properties();
        try {
            FileInputStream envFile = new FileInputStream(this.fileUrl);
            properties.load(envFile);
            envFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Access properties from .env file
        String apiKey = properties.getProperty("API_KEY");
        String apiSecret = properties.getProperty("API_SECRET");
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }
}
