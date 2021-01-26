<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new driver</title>
</head>
<body>
<h1>Hello, please fill the form to create driver</h1>
<h4 style="color:red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/drivers/create">
    Input your driver name: <input type="text" required name="driver_name">
    Input your driver licence number: <input type="text" required name="license_number">
    <br>
    <br>
    Input your driver login: <input type="text" required name="driver_login">
    Input your driver password: <input type="password" required name="driver_password">
    Repeat your password: <input type="password" required name="repeat_driver_password">
    <button type="submit">Create</button>
</form>
</body>
</html>
