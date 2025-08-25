<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Подтверждение заказа</title>
</head>
<body>
    <h1>Ваш заказ оформлен!</h1>

    <!-- Данные получателя -->
    <h2>Данные получателя:</h2>
    <p><strong>Имя:</strong> ${orderData.name}</p>
    <p><strong>Телефон:</strong> ${orderData.phone}</p>
    <p><strong>Адрес:</strong> ${orderData.address}</p>
    <p><strong>Доставка:</strong> ${orderData.delivery}</p>
    <p><strong>Оплата:</strong> ${orderData.payment}</p>

    <!-- Состав заказа -->
    <h2>Состав заказа:</h2>
    <table border="1">
        <tr>
            <th>Название</th>
            <th>Цена</th>
            <th>Количество</th>
            <th>Сумма</th>
        </tr>
        <c:forEach var="entry" items="${orderData.cart.products}">
            <tr>
                <td>${entry.value.name}</td>
                <td>${entry.value.price}</td>
                <td>${orderData.cart.quantities[entry.key]}</td>
                <td>${entry.value.price * orderData.cart.quantities[entry.key]}</td>
            </tr>
        </c:forEach>
    </table>

    <h3>Итого к оплате: <fmt:formatNumber value="${orderData.totalPrice}" pattern="#,###.##" /> руб.</h3>
    <p>Спасибо за заказ! Мы свяжемся с вами в ближайшее время.</p>
    <a href="catalog">Продолжить покупки</a>
</body>
</html>
