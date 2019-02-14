package ru.javawebinar.topjava.util;

import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    //private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTH:m");
    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }
}