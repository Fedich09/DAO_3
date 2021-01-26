<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Here you can see all your cars</title>
</head>
<body>
<h1>Here you can see all cars</h1>
<table border="1">
    <tr>
        <th>Сar Id</th>
        <th>Car model</th>
        <th>Manufacturer name</th>
        <th>Manufacturer country</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>
                <c:out value="${car.id}"/>
            </td>
            <td>
                <c:out value="${car.model}"/>
            </td>
            <td>
                <c:out value="${car.manufacturer.name}"/>
            </td>
            <td>
                <c:out value="${car.manufacturer.country}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
