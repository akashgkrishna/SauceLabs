package org.bookcart.flows;

import io.qameta.allure.Step;
import org.bookcart.model.User;
import org.bookcart.pages.LoginPage;
import org.bookcart.pages.RegisterPage;
import org.bookcart.pages.common.Header;
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
        loginPage.clickOnRegisterButton();
    }

    public void navigateToRegisterPageAndEnterUserDetails(User user) {
        navigateToRegisterPage();
        registerPage.enterNewUserDetails(user);
        registerPage.clickOnRegisterButton();
    }
}
