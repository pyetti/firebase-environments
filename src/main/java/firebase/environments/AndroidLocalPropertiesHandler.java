package main.java.firebase.environments;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class AndroidLocalPropertiesHandler {
    private final String localPropsFileLocation;
    private final Properties properties = new Properties();
    private final Environment environment;
    private final String applicationId;

    AndroidLocalPropertiesHandler(Environment environment, String propertiesFile, String applicationId) {
        if (environment == null) {
            throw new IllegalArgumentException("null input param");
        }

        this.environment = environment;
        this.localPropsFileLocation = propertiesFile;
        this.applicationId = applicationId;
        loadProps();
    }

    private void loadProps() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(this.localPropsFileLocation);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    String getProperty(String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        return properties.getProperty(key);
    }

    boolean addProperty(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("null input params");
        }
        return true;
    }

    Object removeProperty(String key) {
        OutputStream outputStream = null;
        try {
            if (StringUtils.isEmpty(key)) {
                throw new IllegalArgumentException("null input param");
            }

            Object object =  properties.remove(key);
            if (object != null) {
                outputStream = new FileOutputStream(this.localPropsFileLocation);
                properties.store(outputStream, String.format("Removed property %s", key));
            }
            return object;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    boolean setEnvironmentProperties() {
        OutputStream outputStream = null;
        try {
            properties.setProperty("environment", this.environment.name().toLowerCase());
            properties.setProperty("applicationId", this.applicationId);
            outputStream = new FileOutputStream(this.localPropsFileLocation);
            properties.store(outputStream, "Set environment and applicationId props from firebase-environment project");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
