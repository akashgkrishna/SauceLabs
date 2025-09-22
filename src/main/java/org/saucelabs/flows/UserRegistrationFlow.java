package org.saucelabs.flows;

import io.qameta.allure.Step;
import org.saucelabs.model.User;
import org.saucelabs.pages.LoginPage;
import org.saucelabs.pages.RegisterPage;
import org.saucelabs.pages.common.Header;
import org.openqa.selenium.WebDriver;

public class UserRegistrationFlow {
    Header header;
    LoginPage loginPage;
    RegisterPage registerPage;

    public UserRegistrationFlow(WebDriver driver) {
        header = new Header(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }

    @Step("Navigating To RegisterPage")
    public void navigateToRegisterPage() {
        header.clickOnLoginButton();
//        loginPage.clickOnRegisterButton();
    }

    public void navigateToRegisterPageAndEnterUserDetails(User user) {
        navigateToRegisterPage();
        registerPage.enterNewUserDetails(user);
        registerPage.clickOnRegisterButton();
    }
}
