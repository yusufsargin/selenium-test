package com.ceng.shared;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {
    private final String propertyPath = "properties";
    private Properties properties;

    public Property() {
        try(InputStream input = getClass().getClassLoader().getResourceAsStream(propertyPath)) {
            this.properties = new Properties();

            this.properties.load(input);
        } catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
    }

    public String getProperty(String propertyName) {
        return this.properties.getProperty(propertyName);
    }
}
