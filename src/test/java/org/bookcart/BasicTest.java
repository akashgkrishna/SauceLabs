package org.bookcart;

import org.bookcart.base.BaseTest;
import org.testng.annotations.Test;

public class BasicTest extends BaseTest {

    @Test
    public void openBookCart() throws InterruptedException {
        logger.info("Title is: {}", driver.getTitle()); // Prints the page title to the console

        Thread.sleep(2000);

        logger.info("Username: {}", username);
        logger.info("Password: {}", password);
        logger.warn("Warn Example");
        logger.error("Error Example");
    }
}
