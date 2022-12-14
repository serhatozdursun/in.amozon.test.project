package pages;

import driver.DriverManager;
import helpers.ClickHelper;
import helpers.SelectHelper;
import helpers.SwitchHelper;
import helpers.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

import static base.BasePage.getWaitTime;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductListPage {
    private final static Logger log = LogManager.getLogger(HomePage.class);

    public ProductListPage() {
        var ajax = new AjaxElementLocatorFactory(DriverManager.instance().getDriver(), getWaitTime());
        PageFactory.initElements(ajax, this);
        new WaitHelper().waitUntilPageLoad();
        log.info("Home page is loaded");
    }

    @FindBy(xpath = "//span[text()='Samsung']")
    private WebElement samsungBrandLabel;

    @FindBy(id = "s-result-sort-select")
    private WebElement sortSelect;

    @FindBy(xpath = "//span[@class='a-price-whole']")
    private List<WebElement> productPrices;

    public ProductListPage clickSamsungBrandLabel() {
        new ClickHelper().clickElement(samsungBrandLabel);
        return this;
    }

    public ProductListPage sortHighToLow() {
        new SelectHelper().selectByValue(sortSelect, "price-desc-rank");
        return this;
    }

    public ProductListPage checkIfSortedHighToLow() {
        for (int i = 0; i < productPrices.size() - 1; i++) {
            var priceString1 = productPrices.get(i).getText();

            priceString1 = priceString1.length() > 6
                    ? priceString1.replaceFirst(",", "")
                    : priceString1;
            priceString1 = priceString1.replaceAll(",", ".");
            var price1 = Float.parseFloat(priceString1);

            var priceString2 = productPrices.get((i + 1)).getText();

            priceString2 = priceString2.length() > 6
                    ? priceString2.replaceFirst(",", "")
                    : priceString2;
            priceString2 = priceString2.replaceAll(",", ".");
            var price2 = Float.parseFloat(priceString2);
            var message = String.format("""
                    The product list should've sorted high to low,
                    earlier product price is %s,
                    following product price is %s""", priceString1, priceString2);
            assertTrue(price1 >= price2, message);
        }
        log.info("High To Low orders is correct");
        return this;
    }

    public ProductDetails clickDesiredHighestPriceProduct(int index){
        new ClickHelper().clickWithJavaScript(productPrices.get(index));
        new SwitchHelper().switchToWindow();
        log.info("Second highest priced product was clicked");
        return new ProductDetails();
    }
}
