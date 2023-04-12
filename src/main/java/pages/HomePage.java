package pages;

import helpers.ClickHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.ElementHelper.isElementPresent;

public class HomePage extends Page {
    private final static Logger log = LogManager.getLogger(HomePage.class);

    public HomePage() {
        initPage(this);
        log.info("Home page is loaded");
    }


    @FindBy(id = "nav-hamburger-menu")
    private WebElement hamburgerMenu;

    @FindBy(xpath = "//div[text()='TV, Appliances, Electronics']")
    private WebElement tvAppliancesElectronics;

    @FindBy(xpath = "//a[text()='Televisions']")
    private WebElement televisions;

    public HomePage clickHamburgerMenu() {
        new ClickHelper().clickElement(hamburgerMenu);
        log.info("hamburger menu is clicked");
        return this;
    }

    public HomePage scrollToTvAppliancesElectronics() {
        new ClickHelper().clickElement(tvAppliancesElectronics);
        log.info("tvAppliancesElectronics menu is clicked");
        return this;
    }

    public ProductListPage clickTelevisions() {
        if (!isElementPresent(By.xpath("//a[text()='Televisions']")))
            clickHamburgerMenu();
        new ClickHelper().clickElement(televisions);
        log.info("televisions menu is clicked");
        return new ProductListPage();
    }

}
