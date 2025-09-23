package org.saucelabs.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.saucelabs.pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver){
        super(driver);
    }

    // Locators
    By hamburgerMenuButton = By.id("react-burger-menu-btn");
    By logoutButton = By.id("logout_sidebar_link");
    By productsTitle = By.cssSelector(".inventory_item_name ");
    By cartButton = By.id("shopping_cart_container");

    public By addToCartButton(String product){
        return By.xpath(
                "//div[text()='"+product+"']/ancestor::div[@class='inventory_item_description']//button");
    }

    // Methods
    @Step("Clicking on Hamburger menu")
    public void clickOnHamburgerMenu(){
        click(hamburgerMenuButton);
    }

    public String getLogoutText(){
        waitForVisibility(logoutButton);
        return getText(logoutButton);
    }

    public List<WebElement> getAllProducts(){
        return driver.findElements(productsTitle);
    }

    public List<String> getProducts(int count){
        List<String> products = new ArrayList<>();
        for (WebElement element : getAllProducts()){
            products.add(element.getText());
        }
        int limit = Math.min(count, products.size());
        return products.subList(0, limit);
    }

    public void addProductsToCart(List<String> products){
        for (String product : products){
            click(addToCartButton(product));
        }
    }

    public void clickOnCartButton(){
        click(cartButton);
    }
}
