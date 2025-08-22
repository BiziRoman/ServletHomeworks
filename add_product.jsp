<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавить товар</title>
</head>
<body>
    <h1>Добавление товара</h1>

    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

    <form action="add-product" method="post">
        <label>Название: <input type="text" name="name" required></label><br>
        <label>Цена: <input type="number" step="0.01" name="price" required></label><br>
        <button type="submit">Добавить</button>
    </form>

    <a href="catalog">Назад в каталог</a>
</body>
</html>
