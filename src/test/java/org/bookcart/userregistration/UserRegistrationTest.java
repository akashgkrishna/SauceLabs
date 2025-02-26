package org.bookcart.userregistration;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.bookcart.base.BaseTest;
import org.bookcart.model.User;
import org.bookcart.pages.LoginPage;
import org.bookcart.pages.RegisterPage;
import org.bookcart.pages.common.Header;
import org.bookcart.providers.UserDataProviders;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class UserRegistrationTest extends BaseTest {
    @Test(dataProviderClass = UserDataProviders.class, dataProvider = "defaultRegistrationData")
    @Description("Verify that new user is able to register to the website")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("CEL-TC-45")
    public void userRegistrationTest(User user){
        // Arrange
        Header header = new Header(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);


        // Act
        header.clickOnLoginButton();
        loginPage.clickOnRegisterButton();
        registerPage.enterNewUserDetails(user);
        registerPage.clickOnRegisterButton();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleIs("Login"));
        Assert.assertEquals(driver.getTitle(), "Login");

    }
}
