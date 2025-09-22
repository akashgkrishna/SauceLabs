package org.saucelabs.pages.common;

import org.saucelabs.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    // Locators

    // XPath template for the “Add to Cart” button; %s will be replaced by the book title
    private final String addToCartButton =
            "//div[contains(@class, 'card-title')]//a[strong[text()='%s']]" + // added a string.format here
                    "//ancestor::div[contains(@class, 'card-title')]" +
                    "/following-sibling::app-addtocart//button";


    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Methods

    // Builds a By locator for the Add to Cart button of the specified book
    private By addToCartButtonFor(String bookTitle) {
        return By.xpath(String.format(addToCartButton, bookTitle));
    }

    public void addBookToCart(String bookTitle) {
        click(addToCartButtonFor(bookTitle));
    }


}
