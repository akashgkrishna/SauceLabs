package org.saucelabs.flows;

import org.saucelabs.pages.LoginPage;
import org.saucelabs.pages.common.Header;
import org.openqa.selenium.WebDriver;

public class LoginFlow {
    private final Header header;
    private final LoginPage loginPage;

    public LoginFlow(WebDriver driver) {
        this.header = new Header(driver);
        this.loginPage = new LoginPage(driver);
    }

    public void login(String username, String password) {
        header.clickOnLoginButton();
        loginPage.enterCredentials(username, password);
        loginPage.clickOnLoginButton();
    }

}
