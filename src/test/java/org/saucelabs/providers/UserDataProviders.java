package org.saucelabs.providers;

import org.saucelabs.data.UserDataFactory;
import org.testng.annotations.DataProvider;

public class UserDataProviders {

    @DataProvider
    public Object[][] defaultRegistrationData() {
        return new Object[][]{
                {UserDataFactory.defaultUser()}
        };
    }

    @DataProvider
    public Object[][] invalidRegistrationData() {
        return new Object[][]{
                {UserDataFactory.invalidUserName()}
        };
    }

    @DataProvider
    public Object[][] invalidPasswordsRegistrationData() {
        return new Object[][]{
                {UserDataFactory.passwordLessThanEightCharacters()},
                {UserDataFactory.passwordWithoutUpperCase()},
                {UserDataFactory.passwordWithoutNumber()}
        };
    }

    @DataProvider
    public Object[][] mismatchedPasswordRegistrationData() {
        return new Object[][]{
                {UserDataFactory.nonMatchingPasswords()}
        };
    }

}
