package servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.Product;
import org.example.model.ShoppingCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {

    private List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L ,"Товар 1",50.0, "Описание 1", 2));
        products.add(new Product(2L, "Товар 2", 100,"Описание 2", 3));
        products.add(new Product(3L, "Товар 3", 100,"Описание 3", 4));
        return products;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session.getAttribute("cart") == null){
            session.setAttribute("cart", new ShoppingCart());
        }

        List<Product> products = getProducts();
        req.setAttribute("products", products);

        req.getRequestDispatcher("catalog.jsp").forward(req, resp);
    }
}
