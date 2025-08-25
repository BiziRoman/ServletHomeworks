package org.example.servlets;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Cart;
import org.example.model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Получаем или создаем корзину
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // Передаем корзину в request scope
        request.setAttribute("cart", cart);

        // Перенаправляем на JSP страницу
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        try {
            // Получаем параметры
            String productIdStr = request.getParameter("productId");
            String quantityStr = request.getParameter("quantity");

            // Проверяем, что параметры не пустые
            if (productIdStr == null || quantityStr == null) {
                response.sendError(400, "Все поля обязательны для заполнения");
                return;
            }

            // Преобразуем в числа
            int productId = Integer.parseInt(productIdStr);
            int quantity = Integer.parseInt(quantityStr);

            // Базовая проверка значений
            if (productId <= 0 || quantity <= 0) {
                response.sendError(400, "ID и количество должны быть положительными числами");
                return;
            }

            // Получаем продукт
            Product product = getProductById(productId);

            if (product == null) {
                response.sendError(404, "Товар не найден");
                return;
            }

            // Добавляем в корзину
            cart.addProduct(product, quantity);
            response.sendRedirect("cart");

        } catch (NumberFormatException e) {
            response.sendError(400, "Неверный формат числа");
        } catch (Exception e) {
            response.sendError(500, "Внутренняя ошибка сервера");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            response.setStatus(404);
            return;
        }

        try {
            // Получаем параметр
            String productIdStr = request.getParameter("productId");

            if (productIdStr == null) {
                response.sendError(400, "Отсутствует ID товара");
                return;
            }

            // Преобразуем в число
            int productId = Integer.parseInt(productIdStr);

            if (productId <= 0) {
                response.sendError(400, "Неверный ID товара");
                return;
            }

            // Удаляем продукт
            cart.removeProduct(productId);
            response.setStatus(200);
            response.sendRedirect("cart");

        } catch (NumberFormatException e) {
            response.sendError(400, "Неверный формат ID товара");
        }
    }

    private Product getProductById(int id){
        ServletContext context = getServletContext();
        List<Product> products = (List<Product>) context.getAttribute("products");

        if (products == null) {
            return null;
        }

        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
