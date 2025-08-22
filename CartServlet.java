package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.ShoppingCart;

import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null){
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        req.setAttribute("cartItems", cart.getItems());
        req.setAttribute("totalPrice", cart.getTotalPrice());

        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
