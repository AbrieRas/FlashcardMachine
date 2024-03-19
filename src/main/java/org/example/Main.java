package org.example;

// Classes
import org.example.FileHandling.FileHandler;
import org.example.API.GetRequest;

// Libraries
import java.io.File;

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
        var fileHandler = new FileHandler(currentDirectory + envFilePath);

        // TEST API CLIENT
        // get server url
        String requestUrl = fileHandler.getServerUrl();

        // new api client for get request
        var getRequest = new GetRequest(requestUrl);
        String getResponse = getRequest.execute();

        // show result
        System.out.println(getResponse);
    }
}