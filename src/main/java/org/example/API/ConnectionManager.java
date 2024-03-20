package org.example.API;

/* Classes */
import org.example.FileHandling.FileHandler;
import org.example.Resources.Encryption;

/* Libraries */
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Represents connecting to the server
 */
public class ConnectionManager {
    private String apiKey;
    private String apiSecret;
    private String apiLockKey;

    // if no request body (data) argument provided, execute default
    public HttpURLConnection openConnection(String url, String method) throws Exception {
        return openConnection(url, method, "");
    }

    /**
     * Opens a connection to the server using parameters and returns the connection
     *
     * @param url - domain and path to server endpoint
     * @param method - desired request method
     * @param data - desired request body
     *
     * @return HttpURLConnection - connection to the server
     */
    public HttpURLConnection openConnection(String url, String method, String data) throws Exception {
        processEnvFile();

        // Load encryption class
        var encryption = new Encryption();

        // Hash API key and secret
        String hashedApiKey = encryption.hashString(this.apiKey);
        String hashedApiSecret = encryption.hashString(this.apiSecret);

        // Create URL and create http object
        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        // Create JWT token and set header & method
        String jwtToken = encryption.createJwtToken(hashedApiKey, hashedApiSecret, apiLockKey);
        connection.setRequestProperty("Authorization", "Bearer " + jwtToken);
        connection.setRequestMethod(method);
        return connection;
    }

    /**
     * Load .env file and assign API credentials
     */
    private void processEnvFile() {
        var envFile = new FileHandler(getEnvPath());

        // Load .env file and assign API credentials
        envFile.loadEnvFile();
        this.apiKey = envFile.getApiKey();
        this.apiSecret = envFile.getApiSecret();
        this.apiLockKey = envFile.getApiLockKey();
    }

    /**
     * Constructs a path to the .env file and return the result
     *
     * @return String - the exact path to the .env file
     */
    private String getEnvPath() {
        String currentDirectory = System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "main"
                + File.separator + "java"
                + File.separator + "org"
                + File.separator + "example"
                + File.separator;
        String pathToEnvFile = "Resources"
                + File.separator + ".env";

        // provide current directory and path to .env file
        return currentDirectory + pathToEnvFile;
    }
}
