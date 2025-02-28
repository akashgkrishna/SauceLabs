package org.bookcart.providers;

import org.bookcart.data.UserDataFactory;
import org.testng.annotations.DataProvider;

public class UserDataProviders {

    @DataProvider
    public Object[][] defaultRegistrationData() {
        return new Object[][]{
                {UserDataFactory.defaultUser()}
        };
    }
}
