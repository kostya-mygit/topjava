package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {
    List<Meal> list();
    void add(Meal meal);
    void update(Meal meal);
    void delete(int id);
    Meal getById(int id);
}
