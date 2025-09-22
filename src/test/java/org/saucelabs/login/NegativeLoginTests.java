package org.saucelabs.login;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.saucelabs.base.BaseTest;
import org.saucelabs.flows.LoginFlow;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

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
    @Description("Verify that login fails when an invalid credentials is entered")
    @TmsLink("CEL-TC-44")
    @Severity(SeverityLevel.NORMAL)
    public void invalidLoginTest(String username, String password) {
        // Arrange
        LoginFlow loginFlow = new LoginFlow(driver);
        String expectedTitle = "Login";

        // Act
        loginFlow.login(username, password);

        // Assert
        // The login page does not give any error message
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleIs(expectedTitle));
        Assert.assertEquals(driver.getTitle(), expectedTitle);

    }
}
