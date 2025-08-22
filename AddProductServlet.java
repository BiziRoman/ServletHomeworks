package servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Product;
import org.example.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/add_product")
public class AddProductServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddProductServlet.class.getName());

    private static List<Product> products = new ArrayList<>();
    private static long currentID = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null || !user.getRole().equals("admin")){
                resp.sendRedirect("login.jsp");
                return;
            }
            req.getRequestDispatcher("add_product.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.severe("Ошибка при обработке GET запроса: " + e.getMessage());
            resp.sendError(500, "Внутренняя ошибка сервера");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null || !user.getRole().equals("admin")){
                resp.sendRedirect("login.jsp");
                return;
            }
            String name = req.getParameter("name");
            String priceStr = req.getParameter("price");
            String description = req.getParameter("description");
            String quantityStr = req.getParameter("quantity");

            if (name == null || name.trim().isEmpty()){
                throw new ServletException("Название продукта не может быть пустым");
            }

            if (priceStr == null || priceStr.trim().isEmpty()) {
                throw new ServletException("Цена не может быть пустой");
            }

            double price;
            int quantity;
            try {
                price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    throw new ServletException("Цена должна быть положительной");
                }
                quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0){
                    throw new ServletException("Количество должно быть положительным");
                }
            } catch (NumberFormatException e) {
                throw new ServletException("Некорректный формат цены", e);
            }

            Product product = new Product(
            currentID++,
            name,
            price,
            description,
                    quantity
            );

            products.add(product);

            req.setAttribute("successMessage", "Продукт успешно добавлен!");
            resp.sendRedirect("catalog.jsp");
        } catch (Exception e) {
            logger.severe("Ошибка при обработке POST запроса: " + e.getMessage());
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("add_product.jsp").forward(req, resp);
        }
    }
}
