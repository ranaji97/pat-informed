package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop;

    public static String getProperty(String key) throws IOException {
    	FileInputStream fis = new FileInputStream("./resources/pat.properties");
        prop = new Properties();
        prop.load(fis);
        return prop.getProperty(key);
    }
}
