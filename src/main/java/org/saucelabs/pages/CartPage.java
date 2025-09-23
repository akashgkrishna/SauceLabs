package org.saucelabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.saucelabs.pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver){
        super(driver);
    }

    // Locators
    By productLabel = By.cssSelector(".inventory_item_name");


    public List<WebElement> getAllProducts(){
        return driver.findElements(productLabel);
    }

    public List<String> getProductsName(){
        List<String> products = new ArrayList<>();
        for (WebElement element : getAllProducts()){
            products.add(element.getText());
        }
        return products;
    }
}
