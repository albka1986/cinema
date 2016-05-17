package com.cinema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    public static Properties readProperties() {
        Properties properties = null;
        File file = new File("C:\\Users\\Admin\\MailRuCloud\\Java\\IDEA Intellij\\cinema\\src\\application.properties");
        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


}
