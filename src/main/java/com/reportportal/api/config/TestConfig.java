package com.reportportal.config;

import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                     TestConfig.class
                             .getClassLoader()
                             .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException("application.properties not found");
            }
            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public static String uiUrl() {
        return properties.getProperty("ui.url");
    }

    public static String launchesUrl() {
        return properties.getProperty("launches.url");
    }

    public static String apiUrl() {
        return properties.getProperty("api.url");
    }

    public static String project() {
        return properties.getProperty("project.name");
    }

    public static String apiKey() {
        return properties.getProperty("api.key");
    }
}
