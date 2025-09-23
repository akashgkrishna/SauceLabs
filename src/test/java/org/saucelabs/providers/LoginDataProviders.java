package org.saucelabs.providers;

import org.saucelabs.utils.ConfigManager;
import org.testng.annotations.DataProvider;

public class LoginDataProviders {

    protected static final String username = ConfigManager.getProperty("qa.username");
    protected static final String password = ConfigManager.getProperty("qa.password");

    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] invalidLoginCredentials() {
        return new Object[][]{
                {username, "invalidPassword"},
                {"invalidUsername", password},
                {"invalidUsername", "invalidPassword"}
        };
    }

    @DataProvider(name = "lockedOutUser")
    public Object[][] lockedOutUser() {
        return new Object[][]{
                {"locked_out_user", password}
        };
    }

    @DataProvider(name = "performanceGlitchUser")
    public Object[][] performanceGlitchUser() {
        return new Object[][]{
                {"performance_glitch_user", password}
        };
    }
}
