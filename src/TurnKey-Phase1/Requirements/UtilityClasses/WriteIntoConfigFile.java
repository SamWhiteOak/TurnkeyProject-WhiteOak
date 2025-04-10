package UtilityClasses;

import java.io.*;
import java.util.Properties;


public class WriteIntoConfigFile {

    public void modifyProperty(String key, String newValue) {
        Properties properties = new Properties();

        // Load existing properties from file
        String path = System.getProperty("user.dir")+"/Configuration/Config.properties";
        try (InputStream input = new FileInputStream(path)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading configuration properties.");
            return;
        }


        // Modify the specified property
        properties.setProperty(key, newValue);


        // Write the modified properties back to file
        try (OutputStream output = new FileOutputStream(path)) {
            properties.store(output, "Modified Configuration Properties");
            System.out.println("Property '" + key + "' modified successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing modified configuration properties to file.");
        }
    }
}





