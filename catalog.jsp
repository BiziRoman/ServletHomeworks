<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Каталог товаров</title>
</head>
<body>
    <h1>Каталог товаров</h1>

    <c:if test="${sessionScope.user.role == 'admin'}">
        <a href="add_product">Добавить товар</a>
    </c:if>

    <table border="1">
        <tr>
            <th>Название</th>
            <th>Цена</th>
            <th>Действие</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>
                    <form action="add-to-cart" method="post">
                        <input type="hidden" name="productId" value="${product.id}">
                        <button type="submit">В корзину</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="cart">Посмотреть корзину</a>
</body>
</html>
