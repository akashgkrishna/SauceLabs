package org.bookcart.flows;

import org.bookcart.pages.common.Header;
import org.bookcart.pages.common.HomePage;
import org.openqa.selenium.WebDriver;

public class ShoppingCartFlow {
    private final HomePage homePage;
    private final Header header;

    public ShoppingCartFlow(WebDriver driver){
        this.homePage = new HomePage(driver);
        this.header = new Header(driver);
    }


}
