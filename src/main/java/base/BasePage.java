package base;

import driver.DriverManager;
import driver.DriverServiceManager;
import enums.Browsers;
import exeptions.InvalidGridUrl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.remote.service.DriverService;
import utils.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Locale;

public class BasePage implements
        BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private final static Logger log = LogManager.getLogger(BasePage.class);
    private static Boolean isLocal;
    private static URL gridUrl;

    private static DriverService service;

    private static int waitTime;

    public static int getWaitTime() {
        return waitTime;
    }

    private static void setWaitTime(int time) {
        waitTime = time;
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        service.stop();
    }

    @Override
    public void afterEach(ExtensionContext extensionContext){
        DriverManager.instance().quitDriver();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext)  {
        var waitTime = Configuration.instance().getIntegerValueOfProp("wait.time");
        setWaitTime(waitTime);
        // get default browser from config if it is null the default will be chrome
        String browserName = Configuration.instance().getStringValueOfProp("default.browser") != null
                ? Configuration.instance().getStringValueOfProp("default.browser")
                : "chrome";
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

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        if (isLocal)
            DriverManager.instance().createDriver(service.getUrl());
        else
            DriverManager.instance().createDriver(gridUrl);
        log.info("{} is lunched", DriverManager.instance().getBrowsersType());
        DriverManager.instance().getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        DriverManager.instance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(getWaitTime()));
        var baseUrl = Configuration.instance().getStringValueOfProp("base.url");
        DriverManager.instance().getDriver().get(baseUrl);
        DriverManager.instance().getDriver().manage().deleteAllCookies();
        log.info("{} is loaded", baseUrl);
        DriverManager.instance().getDriver().manage().window().fullscreen();
    }
}
