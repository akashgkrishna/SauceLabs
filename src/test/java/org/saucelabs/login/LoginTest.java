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
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    @Description("Verify that a user can log in with valid credentials.")
    @TmsLink("CEL-TC-43")
    @Severity(SeverityLevel.CRITICAL)
    public void validLoginTest() {
        //Arrange
        LoginFlow loginFlow = new LoginFlow(driver);

        //Act
        loginFlow.login(username, password);

        //Assert
        // TODO Make a common method for these kind of scenarios
        // Wait explicitly for title
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleIs("Home"));
        Assert.assertEquals(driver.getTitle(), "Home");
    }
}
