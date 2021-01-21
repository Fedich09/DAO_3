<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new driver</title>
</head>
<body>
<h1>Hello, please fill the form to create driver</h1>
<form method="post" action="${pageContext.request.contextPath}/drivers/create">
    Input your driver name: <input type="text" required name="driver_name">
    Input your driver licence number: <input type="text" required name="license_number">
    <button type="submit">Create</button>
</form>
</body>
</html>
