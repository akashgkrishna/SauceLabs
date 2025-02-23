package org.bookcart.base;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.bookcart.util.ConfigManager;
import org.bookcart.util.CredentialsManager;
import org.bookcart.util.ScreenshotUtils;
import org.bookcart.util.logging.CustomLogger;
import org.bookcart.util.logging.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class BaseTest {
    // Get the environment from system property or default to "qa"
    protected static final String environment = System.getProperty("env", "qa");
    protected static String username;
    protected static String password;
    protected static String baseUrl;
    // Logger for all child classes
    protected final CustomLogger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;

    @BeforeSuite
    public void setupGlobalConfiguration() {
        logger.info("Environment: {}", environment);

        // Fetch credentials for the environment using CredentialsManager
        username = CredentialsManager.getUsername(environment);
        password = CredentialsManager.getPassword(environment);

        if (username == null || password == null) {
            logger.warn("Credentials not found for environment: {}", environment);
            throw new RuntimeException("Credentials are not configured for environment: " + environment);
        }
    }


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximizes the browser window

        // Fetch the corresponding URL from config.properties
        baseUrl = ConfigManager.getProperty(environment + ".url");
        Allure.step("Opened " + baseUrl);

        if (baseUrl == null || baseUrl.isEmpty()) {
            logger.error("Base URL is missing in config.properties for {}", environment);
            throw new RuntimeException("Base URL is not configured in config.properties.");
        }

        // Open the application URL
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            byte[] screenshot = ScreenshotUtils.capture(driver);
            if (screenshot.length > 0) {
                attachScreenshot(screenshot);
            }
        }
        if (driver != null) {
            driver.quit(); // Closes the browser
            logger.info("Browser closed.");
        } else {
            logger.warn("WebDriver instance was null during tear down.");
        }
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    private byte[] attachScreenshot(byte[] screenshot) {
        return screenshot;
    }
}
