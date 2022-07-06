package example.ui.utils;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class PropertiesRead {

    private final static String defaultConfigName = "config.properties";

    private static Properties properties = null;
    private static PropertiesRead instance;
    private static String propsFileName = null;

    private PropertiesRead() {
    }

    public static PropertiesRead getInstance() {
        if (instance == null) {
            instance = new PropertiesRead();
        }
        return instance;
    }

    public static String getPropertyValue(String name, String propertiesFileName) {
        if (System.getProperty(name) != null) {
            return System.getProperty(name);
        }
        return getInstance().getValueFromConfigFile(name, propertiesFileName);
    }

    public static String getPropertyValue(String name) {
        return getPropertyValue(name, defaultConfigName);
    }

    public static Boolean getBooleanValue(String name, String propertiesFileName) {
        return Boolean.parseBoolean(PropertiesRead.getPropertyValue(name, propertiesFileName));
    }

    public static Boolean getBooleanValue(String name) {
        return Boolean.parseBoolean(PropertiesRead.getPropertyValue(name, defaultConfigName));
    }

    public static Long getLongValue(String name, String propertiesFileName) {
        return Long.parseLong(PropertiesRead.getPropertyValue(name, propertiesFileName));
    }

    public static Long getLongValue(String name) {
        return Long.parseLong(PropertiesRead.getPropertyValue(name, defaultConfigName));
    }

    private String getValueFromConfigFile(String key, String propertiesFileName) {
        if (properties == null || !propsFileName.equals(propertiesFileName)) {
            properties = loadConfigFile(propertiesFileName);
            propsFileName = propertiesFileName;
        }

        Object objFromFile = properties.getProperty(key);
        if (objFromFile != null) {
            return Objects.toString(objFromFile);
        } else {
            return null;
        }
    }

    private Properties loadConfigFile(String propertiesFileName) {
        try {
            Properties prop = new Properties();
            prop.load(getClass().getClassLoader().getResourceAsStream(propertiesFileName));
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
