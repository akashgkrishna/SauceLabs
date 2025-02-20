package org.bookcart.login;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import jdk.jfr.Description;
import org.bookcart.base.BaseTest;
import org.bookcart.flows.LoginFlow;
import org.bookcart.util.CredentialsManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class NegativeLoginTests extends BaseTest {
    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] invalidLoginCredentials() {

        // Fetching valid username and password here than getting from BaseTest
        // as DataProvider will run before BeforeMethod
        String env = System.getProperty("env", "qa");
        String username = CredentialsManager.getUsername(env);
        String password = CredentialsManager.getPassword(env);

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

        // Act
        loginFlow.login(username, password);

        // Assert
        // The login page does not give any error message
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.titleIs("Home"));
        Assert.assertEquals(driver.getTitle(), "Home");
    }
}
