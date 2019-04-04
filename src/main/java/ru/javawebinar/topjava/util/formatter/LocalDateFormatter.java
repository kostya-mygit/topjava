package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class LocalDateFormatter implements Formatter<LocalDate> {

    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String print(LocalDate localDate, Locale locale) {
        if (localDate == null) {
            return "";
        }
        return localDate.format(LOCAL_DATE_FORMATTER);
    }

    @Override
    public LocalDate parse(String formatted, Locale locale) {
        if (formatted.length() == 0) {
            return null;
        }
        return LocalDate.parse(formatted, LOCAL_DATE_FORMATTER);
    }
}
