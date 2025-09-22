package org.saucelabs.pages;

import io.qameta.allure.Step;
import org.saucelabs.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    //Locators
    private final By usernameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");
    private final By loginButton = By.id("login-button");

    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Methods
    @Step("Entering Credentials username: {username}, password: [PROTECTED]")
    public void enterCredentials(String username, String password) {
        sendKeys(username, usernameTextField);
        sendKeys(password, passwordTextField);
    }

    public void clickOnLoginButton() {
        click(loginButton);
    }

}
