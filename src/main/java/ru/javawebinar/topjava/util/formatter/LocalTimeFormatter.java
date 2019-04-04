package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class LocalTimeFormatter implements Formatter<LocalTime> {

    private static final DateTimeFormatter LOCAL_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public String print(LocalTime localTime, Locale locale) {
        if (localTime == null) {
            return "";
        }
        return localTime.format(LOCAL_TIME_FORMATTER);
    }

    @Override
    public LocalTime parse(String formatted, Locale locale) {
        if (formatted.length() == 0) {
            return null;
        }
        return LocalTime.parse(formatted, LOCAL_TIME_FORMATTER);
    }
}
