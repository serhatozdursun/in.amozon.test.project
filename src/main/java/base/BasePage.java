package base;

import driver.DriverManager;
import driver.DriverServiceManager;
import enums.Browsers;
import exeptions.InvalidGridUrl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.service.DriverService;
import utils.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Locale;

public class BasePage {
    private final static Logger log = LogManager.getLogger(BasePage.class);
    private static Boolean isLocal;
    private static URL gridUrl;

    private static DriverService service;

    @BeforeAll
    public static void beforeAll() {
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : "chrome";
        Browsers browserType = Browsers.valueOf(browserName.toUpperCase(Locale.ROOT));
        DriverManager.instance().setBrowsersType(browserType);
        var gridUrlString = Configuration.instance().getStringValueOfProp("grid.url");
        if (gridUrlString == null) {
            isLocal = true;
            service = DriverServiceManager.getInstances().getService();
        } else {
            try {
                gridUrl = new URL(gridUrlString);
            } catch (MalformedURLException e) {
                throw new InvalidGridUrl(gridUrlString);
            }
        }
    }

    @BeforeEach
    public void beforeEach() {
        if (isLocal)
            DriverManager.instance().createDriver(service.getUrl());
        else
            DriverManager.instance().createDriver(gridUrl);
        log.info("{} is lunched", DriverManager.instance().getBrowsersType());
        DriverManager.instance().getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        DriverManager.instance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        var baseUrl = Configuration.instance().getStringValueOfProp("base.url");
        DriverManager.instance().getDriver().get(baseUrl);
        DriverManager.instance().getDriver().manage().deleteAllCookies();
        log.info("{} is loaded", baseUrl);
        DriverManager.instance().getDriver().manage().window().fullscreen();
    }

    @AfterEach
    public void afterEach() {
        DriverManager.instance().quitDriver();
    }

    @AfterAll
    public static void afterAll() {
        service.stop();
    }
}
