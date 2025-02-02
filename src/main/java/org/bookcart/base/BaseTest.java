package org.bookcart.base;

import org.bookcart.util.ConfigManager;
import org.bookcart.util.CredentialsManager;
import org.bookcart.util.LoggerManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    // Logger for all child classes
    protected final Logger logger = LoggerManager.getLogger(this.getClass());
    protected WebDriver driver;
    protected String username;
    protected String password;
    private String baseUrl;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximizes the browser window

        // Get the environment from system property or default to "qa"
        String environment = System.getProperty("env", "qa");
        logger.info("Environment: {}", environment);

        // Fetch the corresponding URL from config.properties
        baseUrl = ConfigManager.getProperty(environment + ".url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            logger.error("Base URL is missing in config.properties for {}", environment);
            throw new RuntimeException("Base URL is not configured in config.properties.");
        }

        // Fetch credentials for the environment using CredentialsManager
        username = CredentialsManager.getUsername(environment);
        password = CredentialsManager.getPassword(environment);
        if (username == null || password == null) {
            logger.warn("Credentials not found for environment: {}", environment);
            throw new RuntimeException("Credentials are not configured for environment: " + environment);
        }

        // Open the application URL
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Closes the browser
            logger.info("Browser closed.");
        } else {
            logger.warn("WebDriver instance was null during tear down.");
        }
    }
}
