package org.bookcart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerManager {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}

