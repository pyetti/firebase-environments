package com.deyvoo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

class LocalPropertiesHandler {

    private final Environment environment;
    private final String buildFile;
    private final Properties properties;

    LocalPropertiesHandler(Environment environment, String buildFile) {
        if (environment == null || buildFile == null || buildFile.isEmpty()) {
            throw new IllegalArgumentException("null input params");
        }

        this.environment = environment;
        this.buildFile = buildFile;
        properties = new Properties();
        readProps();
    }

    void readProps() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(this.buildFile);
            properties.load(fis);
        } catch (IOException e) {
            System.out.println(e);
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

    boolean addProperty(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("null input params");
        }
        return true;
    }

    boolean setEnvironmentProperty() {
        OutputStream outputStreams = null;
        try {
            outputStreams = new FileOutputStream(this.buildFile);
            properties.setProperty("environment", this.environment.name().toLowerCase());
            properties.setProperty("applicationId", getApplicationId());
            properties.store(outputStreams, null);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStreams != null) {
                try {
                    outputStreams.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private String getApplicationId() {
        if (this.environment == Environment.DEV) {
            return "com.deyvoo.dev";
        }
        return "com.deyvoo";
    }
}
