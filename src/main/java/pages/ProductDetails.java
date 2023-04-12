package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static helpers.ElementHelper.isElementPresent;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductDetails extends Page {
    private final static Logger log = LogManager.getLogger(ProductDetails.class);

    public ProductDetails() {
        initPage(this);
        log.info("Product page is loaded");
    }

    @FindBy(css = "#feature-bullets h1")
    private WebElement aboutItemTitle;

    @FindBy(css = "#feature-bullets span")
    private List<WebElement> aboutItemText;

    public ProductDetails checkIfAboutUsPresent() {
        assertTrue(isElementPresent(By.id("feature-bullets")));
        log.info("about this item is present on page");
        return this;
    }

    public ProductDetails logAboutItem() {
        log.info(aboutItemTitle.getText());
        aboutItemText
                .stream()
                .map(WebElement::getText)
                .forEach(log::info);
        log.info("about this item is logged");
        return this;
    }


}
