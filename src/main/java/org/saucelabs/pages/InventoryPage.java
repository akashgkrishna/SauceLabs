package org.saucelabs.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.saucelabs.pages.base.BasePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPage extends BasePage {
    // Locators
    By hamburgerMenuButton = By.id("react-burger-menu-btn");
    By logoutButton = By.id("logout_sidebar_link");
    By productsTitle = By.cssSelector(".inventory_item_name ");
    By cartButton = By.id("shopping_cart_container");
    By resetAppStateButton = By.id("reset_sidebar_link");
    By hamburgerMenuCloseButton = By.id("react-burger-cross-btn");
    By shoppingCartBadge = By.cssSelector(".shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // Helper method for "Add to Cart" button locator for a specific product
    public By addToCartButton(String product) {
        return By.xpath(
                "//div[text()='" + product + "']/ancestor::div[@class='inventory_item_description']//button");
    }

    // Methods
    @Step("Open navigation menu")
    public void clickOnHamburgerMenu() {
        logger.info("Clicking on hamburger menu");
        click(hamburgerMenuButton);
    }

    @Step("Get logout option text")
    public String getLogoutText() {
        logger.info("Retrieving logout button text");
        waitForVisibility(logoutButton);
        return getText(logoutButton);
    }

    @Step("Get list of all available products")
    public List<WebElement> getAllProducts() {
        logger.info("Retrieving all product elements from inventory");
        return driver.findElements(productsTitle);
    }

    @Step("Get {count} products from inventory")
    public List<String> getProducts(int count) {
        List<String> products = new ArrayList<>();

        for (WebElement element : getAllProducts()) {
            products.add(element.getText());
        }
        Collections.shuffle(products);
        logger.info("Getting {} random products from inventory", count);
        int limit = Math.min(count, products.size());
        return products.subList(0, limit);
    }

    @Step("Add products to shopping cart")
    public void addProductsToCart(List<String> products) {
        logger.info("Starting to add {} products to cart", products.size());
        for (String product : products) {
            waitForVisibility(addToCartButton(product));
            click(addToCartButton(product));
        }
    }

    @Step("Navigating to Cart")
    public void clickOnCartButton() {
        click(cartButton);
        logger.info("Clicked on Cart icon");
    }

    @Step("Reset app state from menu")
    public void resetAppState() {
        click(hamburgerMenuButton);
        click(resetAppStateButton);
        click(hamburgerMenuCloseButton);
        logger.info("App state reset");
    }

    @Step("Get the cart badge count")
    public int getBadgeCount() {
        if (driver.findElements(shoppingCartBadge).isEmpty()) {
            logger.error("Cart is empty");
            return 0;
        }
        return Integer.parseInt(getText(shoppingCartBadge));
    }

    @Step("Check if cart has products")
    public boolean hasProductsInCart() {
        return getBadgeCount() > 0;
    }

    @Step("Reset app state if cart has products")
    public void resetAppIfNeeded() {
        if (hasProductsInCart()) {
            resetAppState();
            logger.info("App state reset because cart had leftover products");
        } else {
            logger.info("No products in cart, skipping reset");
        }
    }
}
