package org.saucelabs.userregistration;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.saucelabs.base.BaseTest;
import org.saucelabs.flows.UserRegistrationFlow;
import org.saucelabs.model.User;
import org.saucelabs.providers.UserDataProviders;
import org.saucelabs.utils.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseTest {

    @Test(dataProviderClass = UserDataProviders.class,
            dataProvider = "defaultRegistrationData")
    @Description("Verify that new user is able to register to the website")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("CEL-TC-45")

    public void successfulUserRegistrationTest(User user) {
        // Arrange
        UserRegistrationFlow userRegistrationFlow = new UserRegistrationFlow(driver);
        WaitUtils waitUtils = new WaitUtils(driver);

        // Act
        userRegistrationFlow.navigateToRegisterPageAndEnterUserDetails(user);

        String expectedTitle = "Login";
        String actualTitle = waitUtils.waitForTitle(expectedTitle);
        Assert.assertEquals(actualTitle, expectedTitle,
                "Expected " + expectedTitle + " page after registration");

    }
}
