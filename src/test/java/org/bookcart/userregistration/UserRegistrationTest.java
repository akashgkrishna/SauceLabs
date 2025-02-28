package org.bookcart.userregistration;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.bookcart.base.BaseTest;
import org.bookcart.flows.UserRegistrationFlow;
import org.bookcart.model.User;
import org.bookcart.providers.UserDataProviders;
import org.bookcart.util.WaitUtils;
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
