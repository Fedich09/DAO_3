<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Taxi service</h1>
<br><a href="${pageContext.request.contextPath}/login">Log in</a>
<br><a href="${pageContext.request.contextPath}/drivers/create">Create new driver</a>
<br><a href="${pageContext.request.contextPath}/drivers">Display all drivers</a>
<br><a href="${pageContext.request.contextPath}/manufacturers/create">Creat new manufacturer</a>
<br><a href="${pageContext.request.contextPath}/cars/create">Creat new car</a>
<br><a href="${pageContext.request.contextPath}/cars/drivers/add">Add driver to car</a>
</body>
</html>
