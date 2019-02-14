<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Update meal</h2>
<div style="">
<form action = "meals" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="caloriesPerDay" value="${caloriesPerDay}">
    <input type="hidden" name="id" value="${meal.getId()}">
    <label>Date and Time</label>
    <br><input required type="datetime-local" name="dateTime" value="${meal.getDateTime()}">
    <br><label>Description</label>
    <br><select name="description">
        <option value="Завтрак" ${"Завтрак".equals(meal.getDescription()) ? "selected" : ""}>Завтрак</option>
        <option value="Обед" ${"Обед".equals(meal.getDescription()) ? "selected" : ""}>Обед</option>
        <option value="Ужин" ${"Ужин".equals(meal.getDescription()) ? "selected" : ""}>Ужин</option>
    </select>
    <br><label>Calories</label>
    <br><input required type="number" name="calories" value="${meal.getCalories()}" min="0">
    <br><input type="submit" value="Update">
</form>
</div>
</body>
</html>