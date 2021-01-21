<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create your car</title>
</head>
<body>
<h1>Fill the form to create car</h1>
<h4 style="color: #ff0000">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/cars/create">
    Input car model: <input type="text" name="car_model">
    Input car manufacturer id: <input type="number" name="manufacturer_id">
    <button type="submit">Create</button>
</form>
</body>
</html>
