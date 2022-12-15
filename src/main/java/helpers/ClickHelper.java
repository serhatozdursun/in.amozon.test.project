package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import pages.HomePage;

public class ClickHelper extends WaitHelper {

    private final static Logger log = LogManager.getLogger(HomePage.class);


    public void clickElement(WebElement element) {
        log.info("Clicking on \"{}\"", element);
        new ScrollHelper().scrollToElement(element);
        elementToBeClickable(element).click();
    }

    public void clickWithJavaScript(WebElement element) {
        var executor = new JavascriptHelper();
        executor.executeJavascript("arguments[0].click()", element);
        log.info("Clicked on \"{}\"", element);
    }
}
