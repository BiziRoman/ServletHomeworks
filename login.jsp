<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Вход в систему</title>
</head>
<body>
    <h1>Авторизация</h1>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="login" method="post">
        <label>Логин: <input type="text" name="username"></label><br>
        <label>Пароль: <input type="password" name="password"></label><br>
        <button type="submit">Войти</button>
    </form>
</body>
</html>
