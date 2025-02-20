package org.bookcart.login;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.bookcart.base.BaseTest;
import org.bookcart.pages.LoginPage;
import org.bookcart.pages.common.Header;
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
        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);

        //Act
        header.clickOnLoginButton();
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();

        //Assert
        // TODO Make a common method for these kind of scenarios
        // Wait explicitly for title
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleIs("Home"));
        Assert.assertEquals(driver.getTitle(), "Home");
    }
}
