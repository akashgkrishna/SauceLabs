package org.saucelabs.flows;

import org.saucelabs.pages.common.Header;
import org.saucelabs.pages.common.HomePage;
import org.openqa.selenium.WebDriver;

public class ShoppingCartFlow {
    private final HomePage homePage;
    private final Header header;

    public ShoppingCartFlow(WebDriver driver){
        this.homePage = new HomePage(driver);
        this.header = new Header(driver);
    }


}
