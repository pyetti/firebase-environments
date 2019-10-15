package main.java.firebase.environments;

public class IosPlistPropertiesHandler {
    private final String localPropsFileLocation;
    private final Environment environment;

    IosPlistPropertiesHandler(Environment environment, String propertiesFile) {
        if (environment == null) {
            throw new IllegalArgumentException("null input param");
        }

        this.environment = environment;
        this.localPropsFileLocation = propertiesFile;
    }

    public boolean setEnvironmentProperty() {
        return true;
    }
}
