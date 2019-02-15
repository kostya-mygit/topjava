<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<h2>${meal.id == null ? "Create new meal" : "Update meal"}</h2>
<form action="meals" method="post">
    <input type="hidden" name="caloriesPerDay" value="${caloriesPerDay}">
    <input type="hidden" name="id" value="${meal.id}">
    <label>Date and Time</label>
    <br><input type="datetime-local" name="dateTime" value="${meal.dateTime}" required>
    <br><label>Description</label>
    <br><input type="text" name="description" value="${meal.description}" required>
    <br><label>Calories</label>
    <br><input type="number" name="calories" value="${meal.calories}" min="0" required>
    <br><input type="submit" value="Save">
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>