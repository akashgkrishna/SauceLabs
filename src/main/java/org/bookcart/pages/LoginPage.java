package org.bookcart.pages;

import org.bookcart.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    //Locators
    private final By usernameTextField = By.xpath("//input[@placeholder='Username']");
    private final By passwordTextField = By.xpath("//input[@placeholder='Password']");
    private final By loginButton = By.xpath("//span[text()='Login']");

    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Methods
    public void enterCredentials(String username, String password) {
        sendKeys(username, usernameTextField);
        sendKeys(password, passwordTextField);
    }

    public void clickOnLoginButton() {
        click(loginButton);
    }


}
