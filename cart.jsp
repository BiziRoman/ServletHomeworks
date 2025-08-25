<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Корзина</title>
</head>
<body>
    <h1>Корзина покупок</h1>

    <c:if test="${empty cart.products}">
        <p>Корзина пуста</p>
    </c:if>
    <c:if test="${not empty cart.products}">
        <table border="1">
            <tr>
                <th>Название</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Сумма</th>
                <th>Действия</th>
            </tr>
            <c:forEach var="entry" items="${cart.products}">
                <tr>
                    <td>${entry.value.name}</td>
                    <td>${entry.value.price}</td>
                    <td>
                        <form action="/cart" method="post">
                            <input type="hidden" name="productId" value="${entry.key}">
                            <input type="number" name="quantity" value="${cart.quantities[entry.key]}" min="1">
                            <input type="submit" value="Обновить">
                        </form>
                    </td>
                    <td>${entry.value.price * cart.quantities[entry.key]}</td>
                    <td>
                        <form action="/cart" method="post">
                            <input type="hidden" name="productId" value="${entry.key}">
                            <input type="hidden" name="action" value="delete">
                            <input type="submit" value="Удалить">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <h2>Итого: <fmt:formatNumber value="${cart.totalPrice}" pattern="#,###.##" /> руб.</h2>
    <a href="catalog">Продолжить покупки</a>
    <a href="order">Оформить заказ</a>
</body>
</html>
