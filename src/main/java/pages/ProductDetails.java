package pages;

import driver.DriverManager;
import helpers.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

import static base.BasePage.getWaitTime;
import static helpers.ElementHelper.isElementPresent;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDetails {
    private final static Logger log = LogManager.getLogger(ProductDetails.class);

    public ProductDetails() {
        var ajax = new AjaxElementLocatorFactory(DriverManager.instance().getDriver(), getWaitTime());
        PageFactory.initElements(ajax, this);
        new WaitHelper().waitUntilPageLoad();
        log.info("Home page is loaded");
    }

    @FindBy(css = "#feature-bullets h1")
    private WebElement aboutUsTitle;

    @FindBy(css = "#feature-bullets span")
    private List<WebElement> aboutUsText;

    public ProductDetails checkIfAboutUsPresent() {
        assertTrue(isElementPresent(By.id("feature-bullets")));
        return this;
    }

    public ProductDetails logAboutUs() {
        log.info(aboutUsTitle.getText());
        aboutUsText
                .stream()
                .map(WebElement::getText)
                .forEach(System.out::println);
        return this;
    }


}
