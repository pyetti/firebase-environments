package test.java.firebase.environments;

import main.java.firebase.environments.Environment;
import main.java.firebase.environments.FirebaseServicesFileMover;
import main.java.firebase.environments.Platform;

import java.io.File;
import java.io.IOException;

public class TestFirebaseServicesFileMover {
    private static final String destinationFolderAndroid = System.getProperty("user.dir") + "/src/test/resources/destination/android";
    private static final String destinationFolderIos = System.getProperty("user.dir") + "/src/test/resources/destination/ios";

    @Test
    void testInitParamsAndroidDev() {
        FirebaseServicesFileMover fileMover = new FirebaseServicesFileMover(Environment.DEV, Platform.ANDROID, destinationFolderAndroid);
        assertEquals(destinationFolderAndroid + "/" + fileMover.getGoogleServicesFilename(), fileMover.getDestinationFolder().toString());
        assertEquals("environment_files/dev/android/" + fileMover.getGoogleServicesFilename(), fileMover.getEnvironmentFilesFolder().toString());
    }

    @Test
    void testMoveServicesFileAndroidDev() throws IOException {
        FirebaseServicesFileMover fileMover = new FirebaseServicesFileMover(Environment.DEV, Platform.ANDROID, destinationFolderAndroid);
        System.out.println(fileMover.moveGoogleServicesFile());
        File movedFile = new File(destinationFolderAndroid + File.separator + fileMover.getGoogleServicesFilename());
        assertTrue(movedFile.exists());
        boolean deleted = movedFile.delete();
        if (!deleted) {
            FileUtils.forceDelete(movedFile);
        }
    }

    @Test
    void testInitParamsIosDev() {
        FirebaseServicesFileMover fileMover = new FirebaseServicesFileMover(Environment.DEV, Platform.IOS, destinationFolderIos);
        assertEquals(destinationFolderIos + "/" + fileMover.getGoogleServicesFilename(), fileMover.getDestinationFolder().toString());
        assertEquals("environment_files/dev/ios/" + fileMover.getGoogleServicesFilename(), fileMover.getEnvironmentFilesFolder().toString());
    }

    @Test
    void testMoveServicesFileIosDev() throws IOException {
        FirebaseServicesFileMover fileMover = new FirebaseServicesFileMover(Environment.DEV, Platform.IOS, destinationFolderIos);
        System.out.println(fileMover.moveGoogleServicesFile());
        File movedFile = new File(destinationFolderIos + File.separator + fileMover.getGoogleServicesFilename());
        assertTrue(movedFile.exists());
        boolean deleted = movedFile.delete();
        if (!deleted) {
            FileUtils.forceDelete(movedFile);
        }
    }
}
