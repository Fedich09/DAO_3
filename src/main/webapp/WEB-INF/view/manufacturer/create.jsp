<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new manufacture</title>
</head>
<body>
<h1>Fill the form to create manufacturer</h1>
<form method="post" action="${pageContext.request.contextPath}/manufacturers/create">
    Input manufacturer name: <input type="text" name="manufacturer_name">
    Input manufacturer Country: <input type="text" name="manufacturer_country">
    <button type="submit">Create</button>
</form>
</body>
</html>
