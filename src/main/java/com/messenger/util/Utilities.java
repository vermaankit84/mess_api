package com.messenger.util;

import com.messenger.manager.HttpClientManager;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utilities {
    private static final Logger logger = Logger.getLogger(Utilities.class.getName());

    private Utilities() {
    }

    public static String getLocalDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }


    public static void refresh(final String[] urls) {
        for (int i = 0; i < urls.length; i++) {
            final String response = HttpClientManager.getInstance().sendRequest(urls[i]);
            logger.info("url obtained [ " + urls[i] + " ] for refresh at [ " + getLocalDateTime() + " ] response obtained [ " + response + " ] ");
        }
    }
}
