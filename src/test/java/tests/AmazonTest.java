package tests;

import base.BasePage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.HomePage;

@ExtendWith(BasePage.class)
public class AmazonTest {

    @Test
    public void testStructure() {
        var homepage = new HomePage();
        homepage.clickHamburgerMenu()
                .scrollToTvAppliancesElectronics()
                .clickTelevisions()
                .clickSamsungBrandLabel()
                .sortHighToLow()
                .checkIfSortedHighToLow()
                .clickDesiredHighestPriceProduct(1)
                .checkIfAboutUsPresent()
                .logAboutItem();

    }
}
