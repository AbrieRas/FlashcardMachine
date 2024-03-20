package org.example.API;

/* Classes */
import com.google.gson.Gson;
import org.example.Models.Flashcard;

/* Libraries */
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an API GET request
 */
public class GetRequest {
    private final String getUrl;

    /**
     * Creates a GET request and assigns provided URL
     *
     * @param getUrl - url and path to GET request
     */
    public GetRequest(String getUrl) {
        this.getUrl = getUrl;
    }

    /**
     * Performs a GET request and returns the response as a result
     *
     * @return ArrayList<Flashcard> - list of Flashcards received
     */
    public ArrayList<Flashcard> execute() {
        try {
            // Initialize connection
            HttpURLConnection connection = new ConnectionManager().openConnection(this.getUrl, "GET");

            // Bad request/no response
            int responseCode = connection.getResponseCode();
            if (responseCode < 200 || responseCode > 299) {
                return new ArrayList<>();
            }

            // Parse JSON into flashcards
            Gson gson = new Gson();
            Flashcard[] jsonResponse = gson.fromJson(new InputStreamReader(connection.getInputStream()), Flashcard[].class);
            ArrayList<Flashcard> flashcards = new ArrayList<>(List.of(jsonResponse));

            // Disconnect
            connection.disconnect();

            // Provide flashcards
            return flashcards;
        } catch (Exception e) {
            // Could not reach the server
            return new ArrayList<>();
        }
    }
}
