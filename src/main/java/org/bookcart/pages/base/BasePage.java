package org.bookcart.pages.base;

import io.qameta.allure.Step;
import org.bookcart.util.logging.CustomLogger;
import org.bookcart.util.logging.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private static final int TIMEOUT = 10;
    protected final WebDriver driver;
    protected final CustomLogger logger;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.logger = LogManager.getLogger(this.getClass());
    }


    //    TODO Removed @Step so that password is not displayed in report
//    TODO Find a solution for this
//    @Step("Entering in element {locator}")
    public void sendKeys(String text, By locator) {
        WebElement element = waitForClickable(locator);
        element.sendKeys(text);
        logger.info("Entered text in {} ", locator);
    }

    @Step("Click element: {locator}")
    public void click(By locator) {
        WebElement element = waitForClickable(locator);
        element.click();
        logger.info("Clicked {}", locator);
    }

    @Step("Checking {locator} element displayed")
    public boolean isDisplayed(By locator) {
        WebElement element = waitForClickable(locator);
        logger.info("Checking {} element displayed", locator);
        return element.isDisplayed();
    }

    public WebElement waitForClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
