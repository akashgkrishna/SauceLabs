package org.saucelabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.saucelabs.pages.base.BasePage;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver){
        super(driver);
    }

    // Locators
    By hamburgerMenuButton = By.id("react-burger-menu-btn");
    By logoutButton = By.id("logout_sidebar_link");

    // Methods
    public void clickOnHamburgerMenu(){
        click(hamburgerMenuButton);
    }

    public String getLogoutText(){
        waitForVisibility(logoutButton);
        return getText(logoutButton);
    }

}
