<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<table>
    <tr>
        <th>Date and Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Excess</th>
    </tr>
    <c:forEach items="${meals}" var = "meal">
        <tr style="color: ${meal.isExcess() ? "red" : "green"}">
            <td>${meal.getDate()} ${mealTo.getTime()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td>${meal.isExcess()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>