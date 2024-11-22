package mobileautomation.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private Properties properties;

    /**
     * Constructor to initialize and load the properties file.
     * @param filePath Path to the properties file.
     */
    public PropertiesReader(String filePath) {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
    }

    /**
     * Retrieves the value for the given property key.
     * @param key The property key.
     * @return The property value, or null if not found.
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Retrieves the value for the given property key, with a default value.
     * @param key The property key.
     * @param defaultValue The default value if the key is not found.
     * @return The property value, or the default value if not found.
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}