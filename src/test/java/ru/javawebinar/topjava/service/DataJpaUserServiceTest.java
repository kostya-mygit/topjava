package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void getByIdWithMeal() throws Exception {
        User user = service.getByIdWithMeal(ADMIN_ID);
        assertMatch(user, ADMIN);
        List<Meal> meals = user.getMeals();
        MealTestData.assertMatch(meals, ADMIN_MEAL2, ADMIN_MEAL1);
    }

    @Test
    public void getByIdWithMealNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getByIdWithMeal(1000);
    }
}