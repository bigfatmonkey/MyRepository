<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
</head>

<body>

REGISTERED NEW USER:

<ul>
    <li>Login: ${user.login}</li>
    <li>Password: ${user.password}</li>
    <li>Email: ${user.email}</li>
    <li>Role: ${user.role.name}</li>
</ul>
<a href="index.jsp">Go to login page</a>
</body>
</html>
