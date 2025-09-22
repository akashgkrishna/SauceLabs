package org.saucelabs.pages;

import io.qameta.allure.Step;
import org.saucelabs.model.User;
import org.saucelabs.pages.base.BasePage;
import org.saucelabs.utils.MessageUtils;
import org.saucelabs.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    // Basic form element locators
    private final By firstNameTextField = By.xpath("//input[@placeholder='First name']");
    private final By lastNameTextField = By.xpath("//input[@placeholder='Last Name']");
    private final By userNameTextField = By.xpath("//input[@placeholder='User name']");
    private final By passwordTextField = By.xpath("//input[@placeholder='Password']");
    private final By confirmPasswordTextField = By.xpath("//input[@placeholder='Confirm Password']");

    // Gender radio buttons
    private final By maleGenderRadioButton = By.id("mat-radio-0");
    private final By femaleGenderRadioButton = By.id("mat-radio-3-input");

    private final By registerButton = By.xpath("//span[text()='Register']");

    // Error locators using helper method getErrorMessageLocator
    private final By invalidUsernameError = getErrorMessageLocator("invalid.username");
    private final By invalidPasswordError = getErrorMessageLocator("invalid.password");
    private final By passwordNotMatchingError = getErrorMessageLocator("password.not.match");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Helper method that creates a xpath for error messages based on a message key.
     * The message is fetched from messages.properties file using MessageUtils.
     *
     * @param messageKey the key identifying the error message
     * @return a By locator for the error message element
     */
    private By getErrorMessageLocator(String messageKey) {
        String message = MessageUtils.getMessage(messageKey);
        return By.xpath(String.format("//mat-error[normalize-space()='%s']", message));
    }

    @Step("Entering new user details")
    public void enterNewUserDetails(User user) {
        sendKeys(user.getFirstName(), firstNameTextField);
        sendKeys(user.getLastName(), lastNameTextField);
        sendKeys(user.getUserName(), userNameTextField);
        sendKeys(user.getPassword(), passwordTextField);
        sendKeys(user.getConfirmPassword(), confirmPasswordTextField);

        if (user.getGender().equalsIgnoreCase("Female")) {
            click(femaleGenderRadioButton);
        } else {
            click(maleGenderRadioButton);
        }
    }

    public void clickOnRegisterButton() {
        // TEMPORARY: Forced delay to workaround form submission issue
        WaitUtils.forcedDelay(1);
        click(registerButton);
    }

    // Error visibility checks
    @Step("Verify username error is displayed")
    public boolean isUsernameErrorMessageDisplayed() {
        return isDisplayed(invalidUsernameError);
    }

    @Step("Verify password error is displayed")
    public boolean isInvalidPasswordErrorMessageDisplayed() {
        return isDisplayed(invalidPasswordError);
    }

    @Step("Verify password mismatch error is displayed")
    public boolean isPasswordNotMatchingErrorMessageDisplayed() {
        return isDisplayed(passwordNotMatchingError);
    }
}
