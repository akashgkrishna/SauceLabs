package org.saucelabs.shoppingcart;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.saucelabs.base.BaseTest;
import org.saucelabs.flows.LoginFlow;
import org.saucelabs.pages.ShoppingCartPage;
import org.saucelabs.pages.common.Header;
import org.saucelabs.pages.common.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingCartTests extends BaseTest {
    @BeforeMethod
    public void loginToStore() {
        LoginFlow loginFlow = new LoginFlow(driver);
        loginFlow.login(username, password);
    }

    @Test
    @Description("Verify that a user can add a book to the cart")
    @TmsLink("CEL-TC-49")
    @Severity(SeverityLevel.NORMAL)
    public void addSingleBookToCart() {
        // Arrange
        HomePage homePage = new HomePage(driver);
        Header header = new Header(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        String bookTitle = "Harry Potter and the Chamber of Secrets";

        // Act
        homePage.addBookToCart(bookTitle);
        header.clickOnShoppingCartButton();


        // Assert
        Assert.assertTrue(shoppingCartPage.isBookAddedToCart(bookTitle));
        
    }
}