package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class MealServiceMemoryImpl implements MealService {

    private static Map<Integer, Meal> map = new HashMap<>();

    static {
        map.put(1, new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        map.put(2, new Meal(2, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        map.put(3, new Meal(3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        map.put(4, new Meal(4, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        map.put(5, new Meal(5, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        map.put(6, new Meal(6, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public List<Meal> list() {
        return map.values().stream()
                .sorted(Comparator.comparing(Meal::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void add(Meal meal) {
        map.put(meal.getId(), meal);
    }

    @Override
    public void update(Meal meal) {
        map.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        map.remove(id);
    }

    @Override
    public Meal getById(int id) {
        return map.get(id);
    }
}
