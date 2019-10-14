package com.deyvoo;

public class Main {

    public static void main(String[] args) {
        Environment environment = Environment.getEnv(args[0]);
        if (environment == null) {
            return;
        }
        System.out.println("Successfully set environment to " + environment.name());

        LocalPropertiesHandler localPropertiesHandler = new LocalPropertiesHandler(environment, "/Users/peteryetti/development/deyvoo/flutter/deyvoo_v3/deyvoov_3/android/local.properties");
        System.out.println("SETTING ENVIRONMENT PROPERTIES");
        boolean envSet = localPropertiesHandler.setEnvironmentProperty();
        System.out.println("set env: " + envSet);

        FirebaseHandler firebaseHandler = new FirebaseHandler(environment);
        System.out.println("MOVING GOOGLE SERVICES FILE");
        boolean movedGoogleFile = firebaseHandler.moveGoogleServicesFile();
        System.out.println("moved google services file: " + movedGoogleFile);
    }

}
