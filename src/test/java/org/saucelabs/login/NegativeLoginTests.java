package org.saucelabs.login;

import io.qameta.allure.*;
import org.saucelabs.base.BaseTest;
import org.saucelabs.pages.InventoryPage;
import org.saucelabs.pages.LoginPage;
import org.saucelabs.providers.LoginDataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTests extends BaseTest {
    LoginPage loginPage;

    @Test(dataProvider = "invalidLoginCredentials", dataProviderClass = LoginDataProviders.class)
    @Description("Verify login fails with incorrect password")
    @Severity(SeverityLevel.CRITICAL) @Epic("Login") @Feature("Negative Scenarios")
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

    @Test(dataProvider = "lockedOutUser", dataProviderClass = LoginDataProviders.class)
    @Description("Verify locked-out user cannot login")
    @Severity(SeverityLevel.CRITICAL) @Epic("Login") @Feature("Negative Scenarios")
    public void verifyLockedOutUserTest(String username, String password) {
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
    @Severity(SeverityLevel.NORMAL) @Epic("Login") @Feature("Negative Scenarios")
    public void verifyEmptyFieldsTest() {
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
    @Severity(SeverityLevel.NORMAL) @Epic("Login") @Feature("Negative Scenarios")
    public void verifyEmptyPasswordFieldTest() {
        //Arrange
        loginPage = new LoginPage(driver);

        // Act
        loginPage.enterCredentials(username, "");
        loginPage.clickOnLoginButton();

        boolean emptyPasswordFieldErrorDisplayed = loginPage.isEmptyPasswordFieldErrorDisplayed();
        Assert.assertTrue(emptyPasswordFieldErrorDisplayed);
    }

    @Test(dataProvider = "performanceGlitchUser", dataProviderClass = LoginDataProviders.class)
    @Description("Verify login works but page loads slowly")
    @Severity(SeverityLevel.MINOR) @Epic("Login") @Feature("Negative Scenarios")
    public void verifyPerformanceGlitchUser(String username, String password) {
        //Arrange
        loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        // Act
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();

        //Assert
        inventoryPage.clickOnHamburgerMenu();
        String logoutText = inventoryPage.getLogoutText();
        Assert.assertEquals(logoutText, "Logout");
    }

}
