<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver to car</title>
</head>
<body>
<h1>Add drivers to car</h1>
<form method="post" action="${pageContext.request.contextPath}/cars/add">
    Input car id: <input type="number" required name="car_id">
    Input driver id: <input type="number" required name="driver_id">
    <button type="submit">Create</button>
</form>
</body>
</html>
