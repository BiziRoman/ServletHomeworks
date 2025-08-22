package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private List<User> users = new ArrayList<>();

    public void init(){
        users.add(new User("admin","12345", "admin"));
        users.add(new User("user1", "password1", "user"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User authenticatedUser = null;

        for (User user : users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                authenticatedUser = user;
                break;
            }
        }


        if (authenticatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", authenticatedUser);
            response.sendRedirect("catalog");
        } else {
            request.setAttribute("error", "Неверный логин или пароль");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
