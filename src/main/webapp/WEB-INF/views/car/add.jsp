<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver to car</title>
</head>
<body>
<h1>Add drivers to car</h1>
<h4 style="color: #ff0000">${messageCarIdNorExist}</h4>
<h4 style="color: #ff0000">${messageDriverIdNotExist}</h4>
<form method="post" action="${pageContext.request.contextPath}/car/add">
    Input car id: <input type="number" min="1" name="carId">
    Input driver id: <input type="number" min="1" name="driverId">
    <button type="submit">Create</button>
</form>
</body>
</html>
