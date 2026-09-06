package utils;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();
    public static String BASE_URL = readProperty("BASE_URL");



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