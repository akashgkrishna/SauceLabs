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
    protected final WebDriver driver;
    protected final CustomLogger logger;
    private static final int TIMEOUT = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.logger = LogManager.getLogger(this.getClass());
    }

    @Step("Click element: {locator}")
    public void click(By locator){
        WebElement element = waitForClickable(locator);
        element.click();
        logger.info("Clicked {}", locator);
    }

    public WebElement waitForClickable(By locator){
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
