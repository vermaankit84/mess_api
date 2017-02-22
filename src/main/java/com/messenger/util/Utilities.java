package com.messenger.util;

import com.messenger.manager.HttpClientManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

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
        String response = null;
        for (int i = 0; i < urls.length; i++) {
            try {
                response = HttpClientManager.getInstance().sendRequest(urls[i]);
            } catch (Exception e) {
                logger.warn("exception arise while url [ " + urls[i] + " ] ", e);
            }
            logger.info("url obtained [ " + urls[i] + " ] for refresh at [ " + getLocalDateTime() + " ] response obtained [ " + response + " ] ");
        }
    }

    public static String convertToJson(final String str, final String operation) {
        JSONObject jsonObject = new JSONObject();
        final String[] s = str.split(",");
        int counter = 1;
        for (final String d : s) {
            jsonObject.put(counter, d);
            counter = counter + 1;
        }
        return jsonObject.toString();
    }
}
