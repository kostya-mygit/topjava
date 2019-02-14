package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceMemoryImpl;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private MealService mealService = new MealServiceMemoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("display meal list");

        String view = "/views/meals/list.jsp";

        int caloriesPerDay;
        if (request.getParameter("caloriesPerDay") != null) {
            caloriesPerDay = Integer.parseInt(request.getParameter("caloriesPerDay"));
        } else {
            caloriesPerDay = 0;
        }

        List<MealTo> meals = MealsUtil.getFilteredWithExcess(mealService.list(), LocalTime.MIN, LocalTime.MAX, caloriesPerDay);

        request.setAttribute("meals", meals);
        request.setAttribute("caloriesPerDay", caloriesPerDay);
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String view = "/views/meals/list.jsp";
        String action = request.getParameter("action");

        int caloriesPerDay;
        if (request.getParameter("caloriesPerDay") != null) {
            caloriesPerDay = Integer.parseInt(request.getParameter("caloriesPerDay"));
        } else {
            caloriesPerDay = 0;
        }

        if (action != null) {
            if ("showAddForm".equals(action)) {
                log.debug("show add form");
                view = "/views/meals/add.jsp";
            } else if ("showUpdateForm".equals(action)) {
                Meal meal = mealService.getById(Integer.parseInt(request.getParameter("id")));
                log.debug("show update form for meal with id=" + meal.getId());
                request.setAttribute("meal", meal);
                view = "/views/meals/update.jsp";
            } else if ("add".equals(action)) {
                List<Meal> list = mealService.list();
                int id = list.get(list.size() - 1).getId();
                log.debug("add new meal with id=" + id);
                LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), DateTimeUtil.getDateTimeFormatter());
                String description = request.getParameter("description");
                int calories = Integer.parseInt(request.getParameter("calories"));
                mealService.add(new Meal(id, dateTime, description, calories));
                List<MealTo> meals = MealsUtil.getFilteredWithExcess(mealService.list(), LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
                request.setAttribute("meals", meals);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                log.debug("update meal with id=" + id);
                LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), DateTimeUtil.getDateTimeFormatter());
                String description = request.getParameter("description");
                int calories = Integer.parseInt(request.getParameter("calories"));
                mealService.update(new Meal(id, dateTime, description, calories));
                List<MealTo> meals = MealsUtil.getFilteredWithExcess(mealService.list(), LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
                request.setAttribute("meals", meals);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                log.debug("delete meal with id=" + id);
                mealService.delete(id);
                List<MealTo> meals = MealsUtil.getFilteredWithExcess(mealService.list(), LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
                request.setAttribute("meals", meals);
            }

            request.setAttribute("caloriesPerDay", caloriesPerDay);
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
        }
    }
}
