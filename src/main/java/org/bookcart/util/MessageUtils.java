package org.bookcart.util;

import java.util.ResourceBundle;

public class MessageUtils {
    private static final ResourceBundle messages =
            ResourceBundle.getBundle("error-messages");

    public static String getMessage(String key) {
        return messages.getString(key);
    }
}