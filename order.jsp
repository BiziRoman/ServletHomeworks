<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Оформление заказа</title>
</head>
<body>
    <h1>Оформление заказа</h1>

    <!-- Форма оформления заказа -->
    <form action="/order" method="post">
        <!-- Данные заказчика -->
        <label>Имя: <input type="text" name="name" required></label><br>
        <label>Телефон: <input type="text" name="phone" required></label><br>
        <label>Адрес: <textarea name="address" required></textarea></label><br>

        <!-- Данные о доставке и оплате -->
        <label>Способ доставки:
            <select name="delivery">
                <option value="courier">Курьер</option>
                <option value="pickup">Самовывоз</option>
            </select>
        </label><br>
        <label>Способ оплаты:
            <select name="payment">
                <option value="card">Банковская карта</option>
                <option value="cash">Наличными</option>
            </select>
        </label><br>

        <!-- Список товаров -->
        <h2>Состав заказа</h2>
        <table border="1">
            <tr>
                <th>Название</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Сумма</th>
            </tr>
            <c:forEach var="entry" items="${cart.products}">
                <tr>
                    <td>${entry.value.name}</td>
                    <td>${entry.value.price}</td>
                    <td>${cart.quantities[entry.key]}</td>
                    <td>${entry.value.price * cart.quantities[entry.key]}</td>
                </tr>
            </c:forEach>
        </table>

        <h3>Итого: <fmt:formatNumber value="${cart.totalPrice}" pattern="#,###.##" /> руб.</h3>
        <input type="submit" value="Оформить заказ">
    </form>
</body>
</html>
