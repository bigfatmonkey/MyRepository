<%--Главная страница, которую пользователь видит при заходе на сайт--%>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
</head>
<body>
<form action="login.do"
      method="post"
      enctype="application/x-www-form-urlencoded">
    <label for="login">Login:</label>
    <br><input name="login" id="login" type="text"/>
    <br><label for="password">Password</label>
    <br><input name="password" id="password" type="password"/>
    <br><input type="submit"/>
</form>
<h1 style="color:red">${errorMassage} </h1>
// you can be Mike/123 as User or Sara/123 as Admin
<p>
    Or you can <a href="register.do">Register new user</a>
</body>
</html>
