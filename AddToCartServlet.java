package servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Product;
import org.example.model.ShoppingCart;
import org.example.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private List<Product> products = new ArrayList<>();

    @Override
    public void init() {
        // Инициализация тестовых данных с корректным ID
        products.add(new Product(1L, "Товар 1", 1000.0, "Описание 1", 5));
        products.add(new Product(2L, "Товар 2", 2000.0, "Описание 2", 7));
    }

    private Product getProductById(Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("ID продукта не может быть null");
        }

        for (Product product : products) {
            if (product != null && product.getId() != null && product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("catalog");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String productIdStr = request.getParameter("productId");
            if (productIdStr == null || productIdStr.isEmpty()) {
                throw new ServletException("ID продукта не указан");
            }

            Long productId;
            try {
                productId = Long.parseLong(productIdStr);
            } catch (NumberFormatException e) {
                throw new ServletException("Неверный формат ID продукта", e);
            }

            // Получаем продукт по ID
            Product product = getProductById(productId);
            if (product == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Продукт не найден");
                return;
            }

            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }

            // Добавляем товар в корзину
            cart.addItem(product, 1);

            // Добавляем передачу данных в JSP
            request.setAttribute("cartItems", cart.getItems());
            request.setAttribute("totalPrice", cart.getTotalPrice());

            response.sendRedirect("cart.jsp");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Произошла ошибка: " + e.getMessage());
        }
    }

}

