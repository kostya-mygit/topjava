package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names:");
            Arrays.asList(appCtx.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println();

            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));
            System.out.println();

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            System.out.println("getAll()");
            mealRestController.getAll().forEach(System.out::println);
            System.out.println();

            System.out.println("get()");
            System.out.println(mealRestController.get(1));
            System.out.println();

            System.out.println("create()");
            mealRestController.create(new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "new meal", 1000, 1));
            System.out.println();

            System.out.println("update()");
            Meal meal = new Meal(1, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Изменено", 500, 1);
            mealRestController.update(meal, meal.getId());
            System.out.println();

            System.out.println("delete()");
            mealRestController.delete(5);
            System.out.println();

            System.out.println("getAll()");
            mealRestController.getAll().forEach(System.out::println);
            System.out.println();

            System.out.println("getFilteredByDateTime()");
            mealRestController.getFilteredByDateTime(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 31), LocalTime.of(11, 0), LocalTime.of(20, 0)).forEach(System.out::println);
            System.out.println();
        }
    }
}
