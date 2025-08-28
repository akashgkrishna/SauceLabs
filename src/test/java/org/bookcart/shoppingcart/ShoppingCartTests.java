package org.bookcart.shoppingcart;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.bookcart.base.BaseTest;
import org.bookcart.flows.LoginFlow;
import org.bookcart.pages.ShoppingCartPage;
import org.bookcart.pages.common.Header;
import org.bookcart.pages.common.HomePage;
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