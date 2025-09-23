package org.saucelabs.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.saucelabs.pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    // Locators
    By productLabel = By.cssSelector(".inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Methods
    @Step("Get list of all available products in cart")
    public List<WebElement> getAllProducts() {
        logger.info("Retrieving all product elements from cart");
        return driver.findElements(productLabel);
    }

    @Step("Get list of all available products in cart in string")
    public List<String> getProductsName() {
        logger.info("Retrieving all product elements from cart in string");
        List<String> products = new ArrayList<>();
        for (WebElement element : getAllProducts()) {
            products.add(element.getText());
        }
        return products;
    }
}
