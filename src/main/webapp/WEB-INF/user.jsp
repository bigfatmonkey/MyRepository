<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>user page</title>
</head>
<body>

<h1 align="center">
    <header>User Page</header>
    Hello
    ${user.getLogin()} </h1>
<h4 align="center">
    Click <a href="logout.do"> here </a> to logout.
</h4>
</body>
</html>