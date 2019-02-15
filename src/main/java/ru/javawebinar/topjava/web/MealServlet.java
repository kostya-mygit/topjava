package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceMemoryImpl;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private MealService mealService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealService = new MealServiceMemoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String paramId = request.getParameter("id");
        Integer id = null;
        if (!paramId.isEmpty()) {
            id = Integer.parseInt(paramId);
        }
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"), DateTimeUtil.getDateTimeFormatter());
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = new Meal(id, dateTime, description, calories);
        log.debug(meal.isNew() ? "create new meal" : "update meal");
        mealService.save(meal);

        response.sendRedirect("meals?caloriesPerDay=" + request.getParameter("caloriesPerDay"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int caloriesPerDay;
        if (request.getParameter("caloriesPerDay") != null) {
            caloriesPerDay = Integer.parseInt(request.getParameter("caloriesPerDay"));
        } else {
            caloriesPerDay = 0;
        }
        request.setAttribute("caloriesPerDay", caloriesPerDay);

        String action = request.getParameter("action");
        if (action == null) {
            log.info("get all meals");
            List<MealTo> meals = MealsUtil.getFilteredWithExcess(mealService.getAll(), LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
            request.setAttribute("meals", meals);
            request.getRequestDispatcher("/views/meals/list.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = getId(request);
            log.info("delete meal with id=" + id);
            mealService.delete(id);
            response.sendRedirect("meals?caloriesPerDay=" + caloriesPerDay);
        } else {
            Meal meal = null;
            if ("create".equals(action)) {
                meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 0);
            } else if ("update".equals(action)) {
                meal = mealService.getById(getId(request));
            }
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/views/meals/save.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
