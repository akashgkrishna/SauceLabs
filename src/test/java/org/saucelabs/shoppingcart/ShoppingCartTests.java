package org.saucelabs.shoppingcart;

import io.qameta.allure.*;
import org.saucelabs.base.BaseTest;
import org.saucelabs.pages.CartPage;
import org.saucelabs.pages.InventoryPage;
import org.saucelabs.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ShoppingCartTests extends BaseTest {
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    @BeforeMethod
    public void login(){
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        loginPage.login(username, password);
    }

    @Test
    @Description("Verify user can add a product to cart")
    @Severity(SeverityLevel.CRITICAL) @Epic("Shopping Cart") @Feature("Add to Cart")
    public void verifyAddSingleProductToCartTest(){
        // Arrange
        List<String> products= inventoryPage.getProducts(1);

        // Act
        inventoryPage.addProductsToCart(products);
        inventoryPage.clickOnCartButton();

        // Assert
        String productFromCart = cartPage.getProductsName().getFirst();
        Assert.assertEquals(productFromCart, products.getFirst());

    }
}
