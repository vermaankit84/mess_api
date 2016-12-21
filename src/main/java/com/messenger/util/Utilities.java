package com.messenger.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utilities {
    private Utilities() {
    }

    public static String getLocalDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
