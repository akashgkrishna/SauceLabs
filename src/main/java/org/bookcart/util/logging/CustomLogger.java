package org.bookcart.util.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger {
    private final Logger logger;

    public CustomLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    // Standard logging methods
    public void info(String format, Object... args) {
        logger.info(format, args);
    }

    public void error(String format, Object... args) {
        logger.error(format, args);
    }

    public void warn(String format, Object... args) {
        logger.warn(format, args);
    }

}
