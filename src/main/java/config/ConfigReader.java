package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties props;

    static {
        try {
            FileInputStream fis = new FileInputStream("./src/test/resources/config.properties");
            props = new Properties();
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
