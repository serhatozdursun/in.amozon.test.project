package driver;

import browsers.*;
import enums.Browsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.URL;
import java.util.Locale;

public class DriverManager {

    private static DriverManager instances = null;

    private Browsers browsersType;

    private final Logger log = LogManager.getLogger(DriverManager.class);

    private RemoteWebDriver driver;

    private DriverManager() {

    }

    public static DriverManager instance() {
        if (instances == null) {
            instances = new DriverManager();
        }
        return instances;
    }


    public void createDriver(URL driverURL) {
        BrowserSelectable browserSelectable;
        switch (getBrowsersType()) {
            case EDGE -> {
                browserSelectable = new Edge();
                setDriver(browserSelectable.getBrowser(driverURL));
            }
            case SAFARI -> {
                browserSelectable = new Safari();
                setDriver(browserSelectable.getBrowser(driverURL));
                getDriver().manage().window().maximize();
            }
            case FIREFOX -> {
                browserSelectable = new Firefox();
                setDriver(browserSelectable.getBrowser(driverURL));
                getDriver().manage().window().maximize();
            }
            case CHROME -> {
                browserSelectable = new Chrome();
                setDriver(browserSelectable.getBrowser(driverURL));
            }
            default -> throw new IllegalArgumentException(String.format("%s undefined type of browser", getBrowsersType()));

        }
    }


    public void quitDriver() {
        try {
            if (getDriver() != null) {
                getDriver().close();
                getDriver().quit();
            }
        } catch (NoSuchSessionException e) {
            log.warn("Driver already closed");
        }
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    private void setDriver(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public Browsers getBrowsersType() {
        return browsersType;
    }

    public void setBrowsersType(Browsers browsers) {
        this.browsersType = browsers;
    }

}
