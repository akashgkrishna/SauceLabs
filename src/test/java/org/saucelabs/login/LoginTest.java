package org.saucelabs.login;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.saucelabs.base.BaseTest;
import org.saucelabs.pages.InventoryPage;
import org.saucelabs.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    @Description("Verify user can login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void validLoginTest() {
        //Arrange
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        //Act
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();

        //Assert
        inventoryPage.clickOnHamburgerMenu();
        String logoutText = inventoryPage.getLogoutText();
        Assert.assertEquals(logoutText, "Logout");
    }
}
