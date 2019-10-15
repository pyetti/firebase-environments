package main.java.firebase.environments;

public class Main {

    public static void main(String[] args) {
        Environment environment = Environment.getEnv(args[0]);
        String applicationId = args[1];
        Platform platform = Platform.getPlatform(args[2]);
        String propertiesFilePath = args[3];
        String googleServicesFilePath = args[4];
        if (environment == null || platform == null || StringUtils.isEmpty(propertiesFilePath) || StringUtils.isEmpty(googleServicesFilePath) || StringUtils.isEmpty(applicationId)) {
            return;
        }

        System.out.println("Successfully set environment to " + environment.name());
        System.out.println("Successfully set applicationId to " + applicationId);
        System.out.println("Successfully set platform to " + platform.name());
        System.out.println("Successfully set propertiesFilePath to " + propertiesFilePath);
        System.out.println("Successfully set googleServicesFilePath to " + googleServicesFilePath);
        System.out.println();

        if (platform.equals(Platform.ANDROID)) {
            AndroidLocalPropertiesHandler androidLocalPropertiesHandler = new AndroidLocalPropertiesHandler(environment, propertiesFilePath, applicationId);
            System.out.println("SETTING ANDROID ENVIRONMENT PROPERTIES");
            boolean envSet = androidLocalPropertiesHandler.setEnvironmentProperties();
            System.out.println("set android env: " + envSet);
        } else if (platform.equals(Platform.IOS)) {
            IosPlistPropertiesHandler iosPlistPropertiesHandler = new IosPlistPropertiesHandler(environment, propertiesFilePath);
            System.out.println("SETTING IOS ENVIRONMENT PROPERTIES");
            boolean envSet = iosPlistPropertiesHandler.setEnvironmentProperty();
            System.out.println("set ios env: " + envSet);
        }

        FirebaseServicesFileMover firebaseServicesFileMover = new FirebaseServicesFileMover(environment, platform, googleServicesFilePath);
        System.out.println("MOVING GOOGLE SERVICES FILE");
        String fileLocation = firebaseServicesFileMover.moveGoogleServicesFile();
        System.out.println("Moved google services file to: " + fileLocation);
    }

}
