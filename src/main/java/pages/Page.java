package pages;

import driver.DriverManager;
import helpers.WaitHelper;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static base.BasePage.getWaitTime;

public abstract class  Page {
    public void initPage(Object page){
        var ajax = new AjaxElementLocatorFactory(DriverManager.instance().getDriver(), getWaitTime());
        PageFactory.initElements(ajax, page);
        new WaitHelper().waitUntilPageLoad();
    }
}
