package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties properties;

    public ConfigReader() {

        properties = new Properties();

        try {

            String env = System.getProperty("env");

            if (env == null) {

                env = "qa";
            }

            FileInputStream file =
                    new FileInputStream(
                            "src/test/resources/config-" + env + ".properties"
                    );

            properties.load(file);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public String getProperty(String key) {

        return properties.getProperty(key);
    }
}