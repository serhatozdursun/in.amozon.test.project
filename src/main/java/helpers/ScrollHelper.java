package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class ScrollHelper {
    private final JavascriptHelper javascriptHelper;

    public ScrollHelper() {
        javascriptHelper = new JavascriptHelper();
    }
    private final Logger log = LogManager.getLogger(ScrollHelper.class);

    public void scrollToElement(WebElement webElement) {
        String script = "arguments[0].scrollIntoViewIfNeeded();";
        try {
            javascriptHelper.executeJavascript(script, webElement);
        } catch (Exception e) {
            log.warn("Page or browser doesn't support '{}' that js command", script);
        }
    }
}
