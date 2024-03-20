package org.example.FileHandling;

/* Libraries */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Represents communication between application and files/folders
 */
public class FileHandler {
    private final String fileUrl;
    private String apiKey;
    private String apiSecret;
    private String apiLockKey;
    private String serverUrl;

    /**
     * Assigns the provided URL as final file path
     *
     * @param fileUrl - final path to file
     */
    public FileHandler(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * Provides user API key, if found
     *
     * @return String - raw user API key
     */
    public String getApiKey() {
        // key is not present
        if (this.apiKey == null) {
            return "API key not found in .env file: " + this.fileUrl;
        }

        return this.apiKey;
    }

    /**
     * Provides user API secret, if found
     *
     * @return String - raw user API secret
     */
    public String getApiSecret() {
        // secret is not present
        if (this.apiSecret == null) {
            return "API secret not found in .env file: " + this.fileUrl;
        }

        return this.apiSecret;
    }

    /**
     * Provides API lock key, if found
     *
     * @return String - raw API lock key (identical to server API lock key)
     */
    public String getApiLockKey() {
        // lock key is not present
        if (this.apiLockKey == null) {
            return "API lock key not found in .env file: " + this.fileUrl;
        }

        return this.apiLockKey;
    }

    /**
     * Provides server url and path, if found
     *
     * @return String - url and path to server for API requests
     */
    public String getServerUrl() {
        // server url is not present
        if (this.serverUrl == null) {
            return "Server URL not found in .env file: " + this.fileUrl;
        }

        return this.serverUrl;
    }

    /**
     * Scan through .env file and assign API credentials
     */
    public void loadEnvFile() {
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
