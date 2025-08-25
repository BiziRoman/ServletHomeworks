package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.OrderData;

import java.io.IOException;

@WebServlet("/order-confirm")
public class OrderConfirmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("orderData") == null) {
            response.sendRedirect("cart");
            return;
        }

        OrderData orderData = (OrderData) session.getAttribute("orderData");
        request.setAttribute("orderData", orderData);

        // Удаляем данные заказа из сессии после показа подтверждения
        session.removeAttribute("orderData");

        request.getRequestDispatcher("order-confirm.jsp").forward(request, response);

    }

}
