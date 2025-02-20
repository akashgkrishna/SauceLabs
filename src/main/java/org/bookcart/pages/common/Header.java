package org.bookcart.pages.common;

import org.bookcart.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage {
    public Header(WebDriver driver) {
        super(driver);
    }

    // locators
    private final By loginButton = By.xpath("//span[normalize-space()='Login']");

    // methods
    public void clickOnLoginButton(){
        click(loginButton);
    }
}
