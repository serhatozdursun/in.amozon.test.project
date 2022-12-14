package helpers;

import driver.DriverManager;
import org.openqa.selenium.By;

import java.time.Duration;

import static base.BasePage.getWaitTime;

public class ElementHelper {

    public static boolean isElementPresent(By by) {
        DriverManager.instance().getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(1));
        var result = DriverManager.instance().getDriver().findElements(by).size() > 0;
        DriverManager.instance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(getWaitTime()));
        return result;
    }
}
