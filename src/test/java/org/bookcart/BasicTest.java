package org.bookcart;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.bookcart.base.BaseTest;
import org.testng.annotations.Test;

public class BasicTest extends BaseTest {

    @Test
    @Step("Opening BookCart")
    @Severity(SeverityLevel.CRITICAL)
    public void openBookCart() throws InterruptedException {
        logger.info("Title is: {}", driver.getTitle()); // Prints the page title to the console

        Thread.sleep(2000);

        // To make test fail
//        driver.findElement(By.xpath("abc")).click();

        logger.info("Username: {}", username);
        logger.info("Password: {}", password);
        logger.warn("Warn Example");
        logger.error("Error Example");
    }
}
