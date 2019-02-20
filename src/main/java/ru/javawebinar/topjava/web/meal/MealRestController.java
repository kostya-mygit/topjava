package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        return getFilteredByDateTime(null, null, null, null);
    }

    public List<MealTo> getFilteredByDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        startDate = startDate != null ? startDate : LocalDate.MIN;
        endDate = endDate != null ? endDate : LocalDate.MAX;
        startTime = startTime != null ? startTime : LocalTime.MIN;
        endTime = endTime != null ? endTime : LocalTime.MAX;
        log.info("getAll from {} to {} and from {} to {}", startDate, endDate, startTime, endTime);
        return MealsUtil.getFilteredWithExcess(service.getAll(authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY, startDate, endDate, startTime, endTime);
    }

    public Meal get(int id) {
        log.info("get meal with id={}", id);
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal, id, authUserId());
    }

    public void delete(int id) {
        log.info("delete meal with id={}", id);
        service.delete(id, authUserId());
    }

}