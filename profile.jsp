<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Профиль пользователя</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .profile {
            max-width: 600px;
            margin: 0 auto;
        }
        .profile h2 {
            color: #333;
        }
        .profile p {
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="profile">
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                <c:redirect url="login" />
            </c:when>
            <c:otherwise>
                <h2>Профиль пользователя</h2>

                <c:set var="user" value="${sessionScope.user}" />

                <p><strong>Имя пользователя:</strong> ${user.username}</p>
                <p><strong>Роль:</strong> ${user.role}</p>
                <p><strong>Время входа:</strong>
                    <fmt:formatDate value="${user.loginTime}" pattern="dd.MM.yyyy HH:mm:ss" />
                </p>

                <c:if test="${user.role eq 'ADMIN'}">
                    <p><strong>Дополнительные права:</strong>
                        Администратор системы
                    </p>
                </c:if>

                <p>
                    <a href="userinfo">Информация о пользователе</a> |
                    <a href="logout">Выйти</a>
                </p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
