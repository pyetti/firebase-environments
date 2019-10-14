package com.deyvoo;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FirebaseHandler {

    private static String environmentFilesFolder = "../../../environment_files/";
    private static String destinationFile = "../../../../app/google-services.json";
    private static String googleServicesFilename = "google-services.json";
    private Environment environment;

    FirebaseHandler(Environment environment) {
        this.environment = environment;
    }

    boolean moveGoogleServicesFile() {
        if (environment == null) {
            return false;
        }
        try {
            String googleServicesFile = new StringBuilder(environmentFilesFolder).append(File.separator).append(environment.name().toLowerCase()).append(File.separator).append(googleServicesFilename).toString();
            Path path = Files.copy(Paths.get(googleServicesFile), Paths.get(destinationFile), StandardCopyOption.REPLACE_EXISTING);
            return path != null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
