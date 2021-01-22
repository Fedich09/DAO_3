<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<h4 style="color: red">${errorMsg}</h4>
<form method="post" action="${pageContext.request.contextPath}/login">
    Input your login: <input type="text" required name="login">
    Input your password: <input type="password" required name="password">
    <button type="submit">Login</button>
</form>
<br><a href="${pageContext.request.contextPath}/drivers/create">Click here if you haven't driver account</a>
</body>
</html>
