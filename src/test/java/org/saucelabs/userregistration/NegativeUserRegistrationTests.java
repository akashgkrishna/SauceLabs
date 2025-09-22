package org.saucelabs.userregistration;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.saucelabs.base.BaseTest;
import org.saucelabs.flows.UserRegistrationFlow;
import org.saucelabs.model.User;
import org.saucelabs.pages.RegisterPage;
import org.saucelabs.providers.UserDataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeUserRegistrationTests extends BaseTest {

    @Test(dataProviderClass = UserDataProviders.class,
            dataProvider = "invalidRegistrationData")
    @Description("Verify that registration fails when user enters existing username")
    @TmsLink("CEL-TC-46")
    @Severity(SeverityLevel.CRITICAL)
    public void userRegistrationWithInvalidUsernameTest(User user) {
        // Arrange
        UserRegistrationFlow userRegistrationFlow = new UserRegistrationFlow(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        // Act
        userRegistrationFlow.navigateToRegisterPageAndEnterUserDetails(user);

        // Assert
        boolean isErrorMessageDisplayed = registerPage.isUsernameErrorMessageDisplayed();
        Assert.assertTrue(isErrorMessageDisplayed, "Invalid Username error not displayed");
    }

    @Test(dataProviderClass = UserDataProviders.class, dataProvider = "invalidPasswordsRegistrationData")
    @Description("Verify that registration fails with invalid passwords")
    @TmsLink("CEL-TC-47")
    @Severity(SeverityLevel.NORMAL)
    public void userRegistrationWithInvalidPasswordTest(User user){
        // Arrange
        UserRegistrationFlow userRegistrationFlow = new UserRegistrationFlow(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        // Act
        userRegistrationFlow.navigateToRegisterPageAndEnterUserDetails(user);

        // Assert
        boolean isErrorMessageDisplayed = registerPage.isInvalidPasswordErrorMessageDisplayed();
        Assert.assertTrue(isErrorMessageDisplayed, "Invalid Password error not displayed");
    }

    @Test(dataProviderClass = UserDataProviders.class, dataProvider = "mismatchedPasswordRegistrationData")
    @Description("Verify that registration fails with non-matching confirm password")
    @TmsLink("CEL-TC-48")
    @Severity(SeverityLevel.CRITICAL)
    public void userRegistrationWithNonMatchingPasswordTest(User user){
        // Arrange
        UserRegistrationFlow userRegistrationFlow = new UserRegistrationFlow(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        // Act
        userRegistrationFlow.navigateToRegisterPageAndEnterUserDetails(user);

        // Assert
        boolean isErrorMessageDisplayed = registerPage.isPasswordNotMatchingErrorMessageDisplayed();
        Assert.assertTrue(isErrorMessageDisplayed, "Password mismatch error not displayed");
    }
}
