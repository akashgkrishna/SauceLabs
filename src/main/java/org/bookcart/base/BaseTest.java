package org.bookcart.base;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.bookcart.driver.DriverManager;
import org.bookcart.util.ConfigManager;
import org.bookcart.util.CredentialsManager;
import org.bookcart.util.ScreenshotUtils;
import org.bookcart.util.logging.CustomLogger;
import org.bookcart.util.logging.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * Base test class handling test setup/tear down, environment configuration,
 * and common test utilities
 */
public class BaseTest {
    // Get the environment from system property or default to "qa"
    protected static final String environment = System.getProperty("env", "qa");
    protected static String username;
    protected static String password;
    protected static String baseUrl;

    // Logger for all child classes
    protected final CustomLogger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;

    /**
     * Global configuration setup runs once before any tests are executed.
     * It retrieves credentials based on the environment.
     */
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

    /**
     * Setup method that runs before each test.
     * It initializes the WebDriver based on configuration,
     * maximizes the window, and navigates to the base URL.
     */
    @BeforeMethod
    public void setUp() {

        // Get browser from config.properties; default to "chrome" if not specified
        String browser = ConfigManager.getProperty("browser");
        driver = DriverManager.getDriver(browser);
        driver.manage().window().maximize(); // Maximizes the browser window

        // Fetch the corresponding URL from config.properties
        baseUrl = ConfigManager.getProperty(environment + ".url");
        Allure.step("Opened " + baseUrl);

        // Validate that the base URL is properly configured
        if (baseUrl == null || baseUrl.isEmpty()) {
            logger.error("Base URL is missing in config.properties for {}", environment);
            throw new RuntimeException("Base URL is not configured in config.properties.");
        }

        // Open the application URL
        driver.get(baseUrl);
    }

    /**
     * Tear down method that runs after each test.
     * It takes a screenshot if the test fails and then quits the WebDriver instance.
     *
     * @param result the result of the test method execution
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture screenshot for failed tests
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            byte[] screenshot = ScreenshotUtils.capture(driver);
            if (screenshot.length > 0) {
                attachScreenshot(screenshot);
            }
        }

        // Close browser instance
        if (driver != null) {
            driver.quit(); // Closes the browser
            logger.info("Browser closed.");
        } else {
            logger.warn("WebDriver instance was null during tear down.");
        }
    }

    /**
     * Attaches screenshot to Allure report
     */
    @Attachment(value = "Page Screenshot", type = "image/png")
    private byte[] attachScreenshot(byte[] screenshot) {
        return screenshot;
    }
}
