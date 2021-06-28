package Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Config class, retrieve configs from Resources/Config.properties file
 * @version 1.0
 * @author Adibov & Armin Gh
 */
public class Config extends Properties {
    final private static String configFileAddress = "/Resources/Config.properties";
    private static boolean hasInitialize = false;
    final private static Config instance = new Config();

    /**
     * retrieve the given property from the config file
     * @param key given property
     * @return value of the given property
     */
    public static String retrieveProperty(String key) {
        if (!hasInitialize)
            Config.initialize();
        return instance.getProperty(key);
    }

    /**
     * initialize class to make it unable to retrieve configs
     */
    private static void initialize() {
        try {
            FileReader fileReader = new FileReader(Config.configFileAddress);
            instance.load(fileReader);
            hasInitialize = true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
