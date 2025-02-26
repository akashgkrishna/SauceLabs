package org.bookcart.util;

import org.bookcart.util.logging.CustomLogger;
import org.bookcart.util.logging.LogManager;

public class TestUtils {
     public static CustomLogger logger = LogManager.getLogger(TestUtils.class);

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
