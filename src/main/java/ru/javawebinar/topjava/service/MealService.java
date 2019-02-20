package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    Meal create(Meal meal, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

    void update(Meal meal, int id, int userId) throws NotFoundException;

    List<Meal> getAll(int userId);
}