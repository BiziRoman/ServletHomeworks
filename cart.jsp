<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Корзина</title>
    <fmt:setLocale value="ru_RU" />
    <fmt:setTimeZone value="Europe/Moscow" />
</head>
<body>
    <h1>Корзина покупок</h1>

    <c:choose>
        <c:when test="${empty cartItems || cartItems.size() == 0}">
            <p>Корзина пуста</p>
        </c:when>
        <c:otherwise>
            <table border="1" style="width:100%;">
                <tr>
                    <th>Название</th>
                    <th>Цена</th>
                    <th>Количество</th>
                    <th>Сумма</th>
                    <th>Действия</th>
                </tr>
                <c:forEach var="item" items="${cartItems}" varStatus="status">
                    <tr>
                        <td>${item.product.name}</td>
                        <td><fmt:formatNumber value="${item.product.price}" pattern="#,##0.00" /> ₽</td>
                        <td>${item.quantity}</td>
                        <td><fmt:formatNumber value="${item.product.price * item.quantity}" pattern="#,##0.00" /> ₽</td>
                        <td>
                            <a href="remove-from-cart?productId=${item.product.id}">Удалить</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <p>Общая сумма: <fmt:formatNumber value="${totalPrice}" pattern="#,##0.00" /> ₽</p>
        </c:otherwise>
    </c:choose>

    <div style="margin-top: 20px;">
        <a href="catalog">Продолжить покупки</a>
        <c:if test="${not empty cartItems}">
            <a href="checkout">Оформить заказ</a>
        </c:if>
    </div>
</body>
</html>
