package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Configuration instance;
    Properties configProps;
    protected static final Logger log = LogManager.getLogger(Configuration.class);
    static final String PROP_FILE_NAME = "config.properties";


    public static Configuration instance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
    }

    private Configuration() {
        try (InputStream is = ClassLoader.getSystemResourceAsStream(PROP_FILE_NAME)) {
            configProps = new Properties();
            configProps.load(is);
        } catch (Exception e) {
            log.error(e);
        } finally {
            log.info("Properties read finished.");
        }
    }

    public String getStringValueOfProp(String propKey) {
        return configProps.getProperty(propKey);
    }

    public Integer getIntegerValueOfProp(String propKey) {
        var stringValue = configProps.getProperty(propKey);
        return stringValue != null ? Integer.parseInt(stringValue) : null;
    }
}