package org.saucelabs.login;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.saucelabs.base.BaseTest;
import org.saucelabs.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NegativeLoginTests extends BaseTest {
    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] invalidLoginCredentials() {
        return new Object[][]{
                {username, "invalidPassword"},
                {"invalidUsername", password},
                {"invalidUsername", "invalidPassword"}
        };
    }

    @Test(dataProvider = "invalidLoginCredentials")
    @Description("Verify login fails with incorrect password")
    @Severity(SeverityLevel.CRITICAL)
    public void invalidLoginTest(String username, String password) {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);

        // Act
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();

        // Assert
        boolean invalidCredentialsErrorDisplayed = loginPage.isInvalidCredentialsErrorDisplayed();
        Assert.assertTrue(invalidCredentialsErrorDisplayed);
    }
}
