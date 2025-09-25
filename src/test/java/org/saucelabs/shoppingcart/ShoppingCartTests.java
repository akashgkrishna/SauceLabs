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
    public void login() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        loginPage.login(username, password);

        inventoryPage.resetAppIfNeeded();
    }

    @Test
    @Description("Verify user can add a product to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Shopping Cart")
    @Feature("Add to Cart")
    public void verifyAddSingleProductToCartTest() {
        // Arrange
        List<String> products = inventoryPage.getProducts(1);

        // Act
        inventoryPage.addProductsToCart(products);
        inventoryPage.clickOnCartButton();

        // Assert
        String productFromCart = cartPage.getProductNames().getFirst();
        Assert.assertEquals(productFromCart, products.getFirst(),
                "Product in cart do not match the product added.");
    }

    @Test
    @Description("Verify user can add multiple products")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Shopping Cart")
    @Feature("Add to Cart")
    public void verifyAddMultipleProductsToCart() {
        // Arrange
        List<String> products = inventoryPage.getProducts(4);

        // Act
        inventoryPage.addProductsToCart(products);
        inventoryPage.clickOnCartButton();

        // Assert
        boolean areProductsMatching = cartPage.isProductsAddedToCart(products);
        Assert.assertTrue(areProductsMatching,
                "Products in cart do not match the products added.");
    }

    @Test
    @Description("Verify user can remove product from cart")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Shopping Cart")
    @Feature("Remove from Cart")
    public void verifyRemoveProductFromCartTest() {
        // Arrange
        List<String> products = inventoryPage.getProducts(4);

        // Act
        inventoryPage.addProductsToCart(products);
        inventoryPage.clickOnCartButton();
        cartPage.removeProductsFromCart();

        // Assert
        boolean isCartEmpty = cartPage.isCartEmpty();
        Assert.assertTrue(isCartEmpty,
                "Cart is not empty after removing all products.");
    }

    @Test
    @Description("Verify cart badge updates correctly")
    @Severity(SeverityLevel.NORMAL)
    @Epic("Shopping Cart")
    @Feature("Cart UI")
    public void verifyCartBadgeUpdatesCorrectlyTest() {
        // Arrange
        int productsToAddCount = 4;
        List<String> products = inventoryPage.getProducts(productsToAddCount);

        // Act
        inventoryPage.addProductsToCart(products);

        // Assert
        int badgeCount = inventoryPage.getBadgeCount();
        Assert.assertEquals(badgeCount, productsToAddCount,
                "The Badge count does not match the  items added");

    }

    @Test
    @Description("Verify that the items added to cart remain even after logging out" +
            " and logging in again")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Shopping Cart")
    @Feature("Add to Cart / Persistence")
    public void verifyCartPersistenceAfterLogoutAndLoginTest() {
        // Arrange
        List<String> products = inventoryPage.getProducts(2);

        // Act
        inventoryPage.addProductsToCart(products);
        inventoryPage.logout();

        loginPage.login(username, password);
        inventoryPage.clickOnCartButton();

        // Assert
        boolean areProductsMatching = cartPage.isProductsAddedToCart(products);
        Assert.assertTrue(areProductsMatching,
                "Products in cart do not match the products added.");
    }
}
