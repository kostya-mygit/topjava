<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Add new meal</h2>

<form action = "meals" method="post">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="caloriesPerDay" value="${caloriesPerDay}">
    <label>Date and Time</label>
    <br><input required type="datetime-local" name="dateTime">
    <br><label>Description</label>
    <br><select name="description">
        <option value="Завтрак">Завтрак</option>
        <option value="Обед">Обед</option>
        <option value="Ужин">Ужин</option>
    </select>
    <br><label>Calories</label>
    <br><input required type="number" name="calories" value="0" min="0">
    <br><input type="submit" value="Add">
</form>
</body>
</html>