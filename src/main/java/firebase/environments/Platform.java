package main.java.firebase.environments;

public enum Platform {
    ANDROID, IOS;

    static Platform getPlatform(String platform) {
        if (platform == null || platform.isEmpty()) {
            return null;
        }

        if (ANDROID.name().toLowerCase().equals(platform.toLowerCase())) {
            return ANDROID;
        }

        if (IOS.name().toLowerCase().equals(platform.toLowerCase())) {
            return IOS;
        }

        return null;
    }
}
