package org.saucelabs.utils;

import org.saucelabs.utils.logging.CustomLogger;
import org.saucelabs.utils.logging.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    public static CustomLogger logger = LogManager.getLogger(WaitUtils.class);
    public WebDriverWait wait;
    public WebDriver driver;

    public WaitUtils(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public String waitForTitle(String expectedTitle){
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        return driver.getTitle();
    }
    public static void forcedDelay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.warn("TEMPORARY SLEEP USED - Tech Debt");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Forced Delay Failed: ", e);
        }
    }


}
