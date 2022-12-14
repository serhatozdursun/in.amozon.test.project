package helpers;

import driver.DriverManager;

public class SwitchHelper {

    public void switchToWindow() {
       DriverManager.instance().getDriver().close();
        DriverManager.instance().getDriver().getWindowHandles()
                .forEach(w -> DriverManager.instance().getDriver().switchTo().window(w));
    }

}
