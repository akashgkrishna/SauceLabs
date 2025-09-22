package org.saucelabs.pages;

import io.qameta.allure.Step;
import org.saucelabs.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucelabs.utils.MessageUtils;

public class LoginPage extends BasePage {
    // Error messages
    String invalidCredentialsMessage = MessageUtils.getMessage("invalid.credentials");

    //Locators
    private final By usernameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");
    private final By loginButton = By.id("login-button");

    public final By getErrorMessageLocator(String message){
        return By.xpath(String.format("//h3[normalize-space()='%s']",message));
    }

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

    @Step("Clicking on Login button")
    public void clickOnLoginButton() {
        click(loginButton);
    }

    public boolean isInvalidCredentialsErrorDisplayed(){
        return isDisplayed(getErrorMessageLocator(invalidCredentialsMessage));
    }

}
