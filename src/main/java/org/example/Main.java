package org.example;

// Classes
import org.example.API.GetRequest;
import org.example.FileHandling.FileHandler;

// Libraries
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // TEST API CLIENT
        // get url
        String requestUrl = "https://spark-purple-grape.glitch.me/db";

        // new api client for get request
        var getRequest = new GetRequest(requestUrl);
        String getResponse = getRequest.execute();

        // show result
        System.out.println(getResponse);

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
        var fileHandler = new FileHandler(currentDirectory + envFilePath);

        // show result
        System.out.println("My key is: " + fileHandler.getApiKey()
                + "\nMy secret is: " + fileHandler.getApiSecret());
    }
}