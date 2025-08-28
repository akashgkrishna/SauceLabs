package org.bookcart.pages.common;

import org.bookcart.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header extends BasePage {
    // locators
    private final By loginButton = By.xpath("//span[normalize-space()='Login']");
    private final By shoppingCartButton = By.xpath("//mat-icon[text()='shopping_cart']");

    public Header(WebDriver driver) {
        super(driver);
    }

    // methods
    public void clickOnLoginButton() {
        click(loginButton);
    }

    public void clickOnShoppingCartButton() {
        click(shoppingCartButton);
    }


}
