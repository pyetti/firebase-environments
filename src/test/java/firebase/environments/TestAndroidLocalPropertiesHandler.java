package test.java.firebase.environments;

import main.java.firebase.environments.AndroidLocalPropertiesHandler;

public class TestAndroidLocalPropertiesHandler {
    private static final String iosPropsFile = System.getProperty("user.dir") + "/src/test/resources/environment_files/ios/Info.plist";
    private static final String packageNameDev = "com.example.dev";

    @Test
    void testInitializePropsHandlerAndroidDev() {
        final String androidPropsFile = System.getProperty("user.dir") + "/src/test/resources/environment_files/dev/android/local.properties";
        AndroidLocalPropertiesHandler propsHandler = new AndroidLocalPropertiesHandler(Environment.DEV, androidPropsFile, packageNameDev);
        assertEquals("value0", propsHandler.getProperty("key0"));
    }

    @Test
    void testSetEnvPropsAndroidDev() {
        final String androidPropsFile = System.getProperty("user.dir") + "/src/test/resources/environment_files/dev/android/local.properties";
        AndroidLocalPropertiesHandler propsHandler = new AndroidLocalPropertiesHandler(Environment.DEV, androidPropsFile, packageNameDev);
        propsHandler.setEnvironmentProperties();
        assertEquals("dev", propsHandler.getProperty("environment"));
        assertEquals(packageNameDev, propsHandler.getProperty("applicationId"));

        propsHandler.removeProperty("environment");
        propsHandler.removeProperty("applicationId");
        assertNull(propsHandler.getProperty("environment"));
        assertNull(propsHandler.getProperty("applicationId"));
    }
}
