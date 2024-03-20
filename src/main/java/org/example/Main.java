package org.example;

/* Classes */
import org.example.API.GetRequest;
import org.example.FileHandling.FileHandler;
import org.example.Models.Flashcard;

/* Libraries */
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // TEST FILE HANDLER
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

        // new file handler for .env file
        var envFile = new FileHandler(currentDirectory + envFilePath);
        envFile.loadEnvFile();

        // TEST API CLIENT
        // get server url
        String requestUrl = envFile.getServerUrl();

        // new api client for get request
        var getRequest = new GetRequest(requestUrl);
        ArrayList<Flashcard> flashcards = getRequest.execute();

        // show result
        for (Flashcard flashcard : flashcards) {
            System.out.println("Question: " + flashcard.getQuestion());
            System.out.println("Answer: " + flashcard.getAnswer());
            System.out.println("Retention Rating: " + flashcard.getRetentionRating());
            System.out.println();
        }
    }
}