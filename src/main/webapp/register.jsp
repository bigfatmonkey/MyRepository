<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
</head>

<body>

REGISTER NEW USER:

<form action="register.do" method="post">

    <label for="login">Login:</label>
    <br><input name="login" id="login" type="text" value="${login}"/>
    ${errorMap.login}

    <br><label for="password">Password:</label>
    <br><input name="password" id="password" type="password" value="${password}"/>
    ${errorMap.password}

    <br><label for="email">Email:</label>
    <br><input name="email" id="email" type="text" value="${email}"/>
    ${errorMap.email}

    <br><label for="FirstName">FirstName:</label>
    <br><input name="FirstName" id="FirstName" type="text" value="${FirstName}"/>

    <br><label for="LastName">LastName:</label>
    <br><input name="LastName" id="LastName" type="text" value="${LastName}"/>

    <%--<br><label for="BirthDate">BirthDate:</label>--%>
    <%--<br><input name = "BirthDate" id="BirthDate" type="date" value="${BirthDate}" todo />--%>

    <br><label for="role">Role:</label>
    <br><select name="role" id="role" size="1">
    <option selected="selected" value="user">user</option>
    <option value="admin">admin</option>
</select>

    <br><input type="submit"/>
</form>

</body>
</html>
