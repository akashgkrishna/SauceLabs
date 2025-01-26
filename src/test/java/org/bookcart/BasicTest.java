package org.bookcart;


import org.bookcart.base.BaseTest;
import org.testng.annotations.Test;

public class BasicTest extends BaseTest {

    @Test
    public void openBookCart() throws InterruptedException {
        System.out.println("Page title is: " + driver.getTitle()); // Prints the page title to the console

        Thread.sleep(5000);

    }
}
