package org.saucelabs.pages;

import org.saucelabs.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage {
    // Locators
    private final String bookTitle = "//a[text() = '%s']";

    // Constructor
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public By bookTitle(String title) {
        return By.xpath(String.format(bookTitle, title));
    }

    // Methods

    public boolean isBookAddedToCart(String title){
        return isDisplayed(bookTitle(title));
    }

}
