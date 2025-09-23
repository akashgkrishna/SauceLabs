package org.saucelabs.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucelabs.pages.base.BasePage;
import org.saucelabs.utils.MessageUtils;

public class LoginPage extends BasePage {
    //Locators
    private final By usernameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");
    private final By loginButton = By.id("login-button");

    // Helper method for error message locator
    public final By getErrorMessageLocator(String message) {
        return By.xpath(String.format("//h3[normalize-space()='%s']", message));
    }

    // Error messages
    String invalidCredentialsMessage = MessageUtils.getMessage("invalid.credentials");
    String lockedOutUserErrorMessage = MessageUtils.getMessage("locked.out.user");
    String emptyFieldErrorMessage = MessageUtils.getMessage("empty.field");
    String emptyPasswordFieldErrorMessage = MessageUtils.getMessage("empty.password");

    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Methods
    public void enterCredentials(String username, String password) {
        sendKeys(username, usernameTextField);
        sendKeys(password, passwordTextField);
    }

    @Step("Clicking on Login button")
    public void clickOnLoginButton() {
        click(loginButton);
        logger.info("Clicked on login button");
    }

    public void login(String username, String password) {
        logger.info("Attempting login for user: {}", username);
        enterCredentials(username, password);
        clickOnLoginButton();
    }

    @Step("Verify invalid credentials error is displayed")
    public boolean isInvalidCredentialsErrorDisplayed() {
        logger.info("Checking for invalid credentials error");
        return isDisplayed(getErrorMessageLocator(invalidCredentialsMessage));
    }

    @Step("Verify locked out user error is displayed")
    public boolean isLockedOutUserErrorDisplayed() {
        logger.info("Checking for locked out user error");
        return isDisplayed(getErrorMessageLocator(lockedOutUserErrorMessage));
    }

    @Step("Verify empty field error is displayed")
    public boolean isEmptyFieldErrorDisplayed() {
        logger.info("Checking empty field error");
        return isDisplayed(getErrorMessageLocator(emptyFieldErrorMessage));
    }

    @Step("Verify empty password field error is displayed")
    public boolean isEmptyPasswordFieldErrorDisplayed() {
        logger.info("Checking empty password field error");
        return isDisplayed(getErrorMessageLocator(emptyPasswordFieldErrorMessage));
    }
}
