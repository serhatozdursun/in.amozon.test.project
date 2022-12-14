package helpers;

import driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;

public class JavascriptHelper {

    public void executeJavascript(String script, Object... objects) {
        ((JavascriptExecutor) DriverManager.instance().getDriver()).executeScript(script, objects);
    }
}
