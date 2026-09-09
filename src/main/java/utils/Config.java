package utils;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    public static String BASE_URL = readProperty("BASE_URL");
    public static String POSTGRES_USER = readProperty("POSTGRES_USER");
    public static String POSTGRES_PASSWORD = readProperty("POSTGRES_PASSWORD");
    public static String POSTGRES_URL = readProperty("POSTGRES_URL");

    private static String readProperty(String propertyName) {
        try {
            String value;
            properties.load(new BufferedReader(new FileReader(new File("src/main/resources/config"))));
            value = properties.getProperty(propertyName);
            if (value == null) {
                throw new RuntimeException("Could not read property " + propertyName);
            } else {
                return properties.getProperty(propertyName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot find config file because of the following exception: " + e);
        }
    }
}