<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>admin page</title>
</head>
<body>
<h1 align="center">
    <header>Admin Page</header>
    Hello
    ${user.getLogin()} </h1>
<h4 align="center">
    Click <a href="logout.do"> here </a> to logout.
</h4>
</body>
</html>