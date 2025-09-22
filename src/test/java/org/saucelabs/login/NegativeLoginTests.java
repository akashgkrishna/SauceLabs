package org.saucelabs.login;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.saucelabs.base.BaseTest;
import org.saucelabs.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NegativeLoginTests extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(NegativeLoginTests.class);
    LoginPage loginPage;
    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] invalidLoginCredentials() {
        return new Object[][]{
                {username, "invalidPassword"},
                {"invalidUsername", password},
                {"invalidUsername", "invalidPassword"}
        };
    }

    @DataProvider(name = "lockedOutUser")
    public Object[][] lockedOutUser() {
        return new Object[][]{
                {"locked_out_user", password}
        };
    }

    @Test(dataProvider = "invalidLoginCredentials")
    @Description("Verify login fails with incorrect password")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyInvalidLoginTest(String username, String password) {
        // Arrange
        loginPage = new LoginPage(driver);

        // Act
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();

        // Assert
        boolean invalidCredentialsErrorDisplayed = loginPage.isInvalidCredentialsErrorDisplayed();
        Assert.assertTrue(invalidCredentialsErrorDisplayed);
    }

    @Test(dataProvider = "lockedOutUser")
    @Description("Verify locked-out user cannot login")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyLockedOutUserTest(String username, String password){
        //Arrange
        loginPage = new LoginPage(driver);

        // Act
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();

        boolean lockedOutUserErrorDisplayed = loginPage.isLockedOutUserErrorDisplayed();
        Assert.assertTrue(lockedOutUserErrorDisplayed);
    }

    @Test
    @Description("Verify login fails when fields are empty")
    @Severity(SeverityLevel.NORMAL)
    public void verifyEmptyFieldsTest(){
        //Arrange
        loginPage = new LoginPage(driver);

        // Act
        loginPage.clickOnLoginButton();

        //Assert
        boolean emptyFieldErrorDisplayed = loginPage.isEmptyFieldErrorDisplayed();
        Assert.assertTrue(emptyFieldErrorDisplayed);
    }

    @Test
    @Description("Verify login fails with blank password")
    @Severity(SeverityLevel.NORMAL)
    public void verifyEmptyPasswordFieldTest(){
        //Arrange
        loginPage = new LoginPage(driver);

        // Act
        loginPage.enterCredentials(username, "");
        loginPage.clickOnLoginButton();

        boolean emptyPasswordFieldErrorDisplayed = loginPage.isEmptyPasswordFieldErrorDisplayed();
        Assert.assertTrue(emptyPasswordFieldErrorDisplayed);
    }
}
