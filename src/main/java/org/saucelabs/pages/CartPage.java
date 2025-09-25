package org.saucelabs.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.saucelabs.pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    InventoryPage inventoryPage = new InventoryPage(driver);

    // Locators
    By productLabel = By.cssSelector(".inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Helper method for "Remove" button locator for a specific product
    public By cartProductRemoveButton(String product) {
        return By.xpath(
                "//div[text()='" + product + "']/ancestor::div[@class='cart_item']//button");
    }

    // Methods
    @Step("Get list of all available products in cart")
    public List<WebElement> getAllProducts() {
        logger.info("Retrieving all product elements from cart");
        return driver.findElements(productLabel);
    }

    @Step("Get list of all available products in cart in string")
    public List<String> getProductNames() {
        logger.info("Retrieving all product elements from cart in string");
        List<String> products = new ArrayList<>();
        for (WebElement element : getAllProducts()) {
            products.add(element.getText());
        }
        return products;
    }

    @Step("Verify if added products match products in the cart")
    public boolean isProductsAddedToCart(List<String> expectedProducts) {

        logger.info("Comparing expected products with products in the cart");
        List<String> productsFromCart = getProductNames();

        if (expectedProducts.size() != productsFromCart.size()) {
            logger.error("Product count mismatch. Expected: {}, Actual: {}",
                    expectedProducts.size(), productsFromCart.size());
            return false;
        }

        return expectedProducts.stream().sorted().toList().equals(
                productsFromCart.stream().sorted().toList());
    }

    @Step("Remove product from cart")
    public void removeProductFromCart(String product) {
        click(cartProductRemoveButton(product));
        logger.info("Removed {} from cart", product);
    }

    @Step("Remove products from cart")
    public void removeProductsFromCart() {
        if (inventoryPage.getBadgeCount() < 1) {
            logger.error("Cart is empty. Cannot remove products.");
            throw new IllegalStateException("Cart is empty. No products to remove.");
        }

        // If cart is not empty
        for (String product : getProductNames()) {
            removeProductFromCart(product);
        }
        logger.info("All products removed from cart");
    }

    @Step("Check if cart is empty")
    public boolean isCartEmpty() {
        logger.info("Checking if cart is empty");
        return getAllProducts().isEmpty();
    }
}
