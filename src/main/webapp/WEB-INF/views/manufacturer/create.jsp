<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new manufacture</title>
</head>
<body>
<h1>Fill the form to create manufacturer</h1>
<form method="post" action="${pageContext.request.contextPath}/manufacturer/create">
    Input manufacturer name: <input type="text" min="3" name="manufacturerName">
    Input manufacturer Country: <input type="text" min="3" name="manufacturerCountry">
    <button type="submit">Create</button>
</form>
</body>
</html>
