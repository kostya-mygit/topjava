package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends MealServiceTest {

    @Test
    public void getByIdWithUser() throws Exception {
        Meal meal = service.getByIdWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MealTestData.assertMatch(meal, ADMIN_MEAL1);
        UserTestData.assertMatch(meal.getUser(), ADMIN);
    }

    @Test
    public void getByIdWithUserNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getByIdWithUser(MEAL1_ID, ADMIN_ID);
    }
}