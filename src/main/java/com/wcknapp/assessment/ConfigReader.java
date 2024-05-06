package com.wcknapp.assessment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static Properties read(String fileName) {
        var reader = new ConfigReader();
        return reader.loadConfiguration(fileName);
    }

    public Properties loadConfiguration(String fileName) {
        var properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Unable to find " + fileName);
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Unable to load configuration file");
        }

        return properties;
    }
}