package main.java.firebase.environments;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FirebaseServicesFileMover {
    private static final String GOOGLE_SERVICES_JSON = "google-services.json";
    private static final String GOOGLE_SERVICE_INFO_PLIST = "GoogleService-Info.plist";
    private final StringBuilder environmentFilesFolder = new StringBuilder();
    private final String googleServicesFilename;
    private final StringBuilder destinationFolder = new StringBuilder();
    private final Environment environment;
    private final Platform platform;

    FirebaseServicesFileMover(Environment environment, Platform platform, String destinationFolder) {
        if (environment == null || platform == null || StringUtils.isEmpty(destinationFolder)) {
            throw new IllegalArgumentException("null input params");
        }

        this.environment = environment;
        this.platform = platform;
        this.destinationFolder.append(destinationFolder);
        if (platform.equals(Platform.ANDROID)) {
            googleServicesFilename = GOOGLE_SERVICES_JSON;
            initParams(GOOGLE_SERVICES_JSON);
        } else {
            googleServicesFilename = GOOGLE_SERVICE_INFO_PLIST;
            initParams(GOOGLE_SERVICE_INFO_PLIST);
        }
    }

    private void initParams(String googleServicesFilename) {
        if (destinationFolder.toString().endsWith("/")) {
            destinationFolder.append(googleServicesFilename);
        } else {
            destinationFolder.append(File.separator).append(googleServicesFilename);
        }

        environmentFilesFolder.append("environment_files").append(File.separator).append(environment.name().toLowerCase())
                .append(File.separator).append(platform.name().toLowerCase()).append(File.separator).append(this.googleServicesFilename);
    }

    String moveGoogleServicesFile() {
        InputStream inputStream = null;
        try {
            inputStream = ClassLoader.getSystemResourceAsStream(environmentFilesFolder.toString());
            if (inputStream == null) {
                return "";
            }
            long bytesRead = Files.copy(inputStream, Paths.get(destinationFolder.toString()), StandardCopyOption.REPLACE_EXISTING);
            if (bytesRead > 0) {
                return destinationFolder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public StringBuilder getEnvironmentFilesFolder() {
        return environmentFilesFolder;
    }

    public String getGoogleServicesFilename() {
        return googleServicesFilename;
    }

    public StringBuilder getDestinationFolder() {
        return destinationFolder;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Platform getPlatform() {
        return platform;
    }
}
