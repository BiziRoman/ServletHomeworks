package org.example.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Cart;
import org.example.model.OrderData;

import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getProducts().isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Получаем данные заказа
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String delivery = request.getParameter("delivery");
            String payment = request.getParameter("payment");

            // Валидация обязательных полей
            if (isFieldEmpty(name) || isFieldEmpty(phone) || isFieldEmpty(address)) {
                response.sendError(400, "Все поля обязательны для заполнения");
                return;
            }

            // Получаем корзину
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null || cart.getProducts().isEmpty()) {
                response.sendError(400, "Корзина пуста");
                return;
            }

            // Сохраняем заказ
            OrderData orderData = new OrderData(name, phone, address, delivery, payment, cart);

            // Очищаем корзину
            session.removeAttribute("cart");

            // Сохраняем данные заказа в сессию
            session.setAttribute("orderData", orderData);

            response.sendRedirect("order-confirm");

        } catch (Exception e) {
            response.sendError(500, "Произошла ошибка при оформлении заказа");
        }
    }

    private boolean isFieldEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}
