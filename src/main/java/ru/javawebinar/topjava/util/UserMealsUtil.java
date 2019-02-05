package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        List<UserMealWithExceed> userMealWithExceedList = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        userMealWithExceedList.forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        /*for (UserMeal userMeal : mealList) {
            LocalDate date = userMeal.getDateTime().toLocalDate();
            if (map.containsKey(date)) {
                map.put(date, map.get(date) + userMeal.getCalories());
            } else {
                map.put(date, userMeal.getCalories());
            }
        }
        mapDateAndCalories.forEach((key, value) -> System.out.println(key + " - " + value));
        */

        Map<LocalDate, Integer> mapDateAndCalories = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            LocalDate date = userMeal.getDateTime().toLocalDate();
            int calories = userMeal.getCalories();
            mapDateAndCalories.merge(date, calories, (oldVal, newVal) -> oldVal + newVal);
        }

        List<UserMealWithExceed> userMealWithExceedList = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            LocalDate date = userMeal.getDateTime().toLocalDate();
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                if (mapDateAndCalories.get(date) > caloriesPerDay) {
                    userMealWithExceedList.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
                } else {
                    userMealWithExceedList.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false));
                }
            }
        }

        return userMealWithExceedList;
    }
}
