package tests;

import base.BasePage;
import org.junit.jupiter.api.Test;
import pages.HomePage;

public class AmazonTest extends BasePage {

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
