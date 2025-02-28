package org.bookcart.driver;

import org.bookcart.util.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    /**
     * Returns a WebDriver instance based on the provided browser name.
     * It reads a headless flag from the configuration and sets up the
     * browser options from config.properties.
     *
     * @param browser the browser name from configuration
     *                (e.g., "chrome", "firefox", "edge", "safari")
     * @return a configured WebDriver instance
     * @throws IllegalArgumentException For unsupported browsers
     */
    public static WebDriver getDriver(String browser) {

        // Default to Chrome if no browser specified
        if (browser == null || browser.trim().isEmpty()) {
            browser = "chrome";
        }

        // Read the headless option from config.properties;
        String headlessStr = ConfigManager.getProperty("headless");
        boolean isHeadless = Boolean.parseBoolean(headlessStr); //defaults to false if not set

        // Create and return the appropriate WebDriver instance based on the browser name.
        return switch (browser.toLowerCase()) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (isHeadless) {
                    options.addArguments("--headless");
                }
                yield new ChromeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                if (isHeadless) {
                    options.addArguments("--headless");
                }
                yield new FirefoxDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                if (isHeadless) {
                    options.addArguments("--headless");
                }
                yield new EdgeDriver(options);
            }
            case "safari" -> new SafariDriver();  // Safari does not support headless mode
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
