<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>List meals</h2>
<form action="meals" method="post">
    <input type="hidden" name="action" value="showAddForm">
    <input type="hidden" name="caloriesPerDay" value="${caloriesPerDay}">
    <input type="submit" value="Add new meal">
</form>
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
        <tr style="color: ${meal.isExcess() ? "red" : "green"}">
            <td hidden>${meal.getId()}</td>
            <td>${meal.getDate()} ${meal.getTime()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td>${meal.isExcess()}</td>
            <td><form action = "meals" method="post">
                <input type="hidden" name="action" value="showUpdateForm">
                <input type="hidden" name="caloriesPerDay" value="${caloriesPerDay}">
                <input type="hidden" name="id" value="${meal.getId()}">
                <input type="submit" value="Update" style="float:left">
            </form></td>
            <td><form action="meals" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="caloriesPerDay" value="${caloriesPerDay}">
                <input type="hidden" name="id" value="${meal.getId()}">
                <input type="submit" value="Delete" style="float:left">
            </form></td>
        </tr>
    </c:forEach>
</table>
<br>
<label>Calories per day: ${caloriesPerDay}</label>
</body>
</html>