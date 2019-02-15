<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>List meals</h2>
<a href="meals?action=create&caloriesPerDay=${caloriesPerDay}">Create new meal</a>
<table>
    <tr>
        <th hidden></th>
        <th>Date and Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Excess</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr style="color: ${meal.excess ? "red" : "green"}">
            <td hidden>${meal.id}</td>
            <td>${meal.getDate()} ${meal.getTime()}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td>${meal.excess}</td>
            <td><a href="meals?action=update&id=${meal.id}&caloriesPerDay=${caloriesPerDay}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}&caloriesPerDay=${caloriesPerDay}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<label>Calories per day: ${caloriesPerDay}</label>
</body>
</html>