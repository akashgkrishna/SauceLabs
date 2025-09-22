package org.saucelabs.utils;

public class CredentialsManager {
    public static String getUsername(String environment) {
        return ConfigManager.getProperty(environment + ".username");
    }

    public static String getPassword(String environment) {
        return ConfigManager.getProperty(environment + ".password");
    }
}
