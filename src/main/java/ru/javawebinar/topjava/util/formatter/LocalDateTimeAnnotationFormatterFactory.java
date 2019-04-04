package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LocalDateTimeAnnotationFormatterFactory implements AnnotationFormatterFactory<LocalDateTimeAnnotation> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        return new HashSet<>(Arrays.asList(LocalDate.class, LocalTime.class));
    }

    @Override
    public Printer<?> getPrinter(LocalDateTimeAnnotation annotation, Class<?> fieldType) {
        return configureFormatterFrom(annotation, fieldType);
    }

    @Override
    public Parser<?> getParser(LocalDateTimeAnnotation annotation, Class<?> fieldType) {
        return configureFormatterFrom(annotation, fieldType);
    }

    private Formatter<?> configureFormatterFrom(LocalDateTimeAnnotation annotation, Class<?> fieldType) {
        if (annotation.type() == LocalDateTimeAnnotation.TYPE.DATE) {
            return new LocalDateFormatter();
        }
        else if (annotation.type() == LocalDateTimeAnnotation.TYPE.TIME) {
            return new LocalTimeFormatter();
        }
        return null;
    }
}
