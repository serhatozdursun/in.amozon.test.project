package helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectHelper extends WaitHelper {


    private Select getSelect(WebElement element) {
        var selectElm = elementToBeClickable(element);
        return new Select(selectElm);
    }

    public void selectByValue(WebElement element, String value) {
        getSelect(element).selectByValue(value);
    }
}
