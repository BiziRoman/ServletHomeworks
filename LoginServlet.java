package org.example;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import user.User;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Map<String, String> users;
    private Map<String, String> roles;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        users = new HashMap<>();
        roles = new HashMap<>();

        users.put("admin", "12345");
        roles.put("admin", "ADMIN");

        users.put("user1", "password1");
        roles.put("user1", "USER");

        users.put("user2", "password2");
        roles.put("user2", "USER");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>Вход в систему</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h2>Форма входа</h2>");
        response.getWriter().println("<form method='post'>");
        response.getWriter().println("  <label>Логин: <input type='text' name='username'></label><br>");
        response.getWriter().println("  <label>Пароль: <input type='password' name='password'></label><br>");
        response.getWriter().println("  <input type='submit' value='Войти'>");
        response.getWriter().println("</form>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            HttpSession session = request.getSession();

            User user = new User();
            user.setUsername(username);
            user.setRole(roles.get(username));
            user.setLoginTime(new Date());

            session.setAttribute("user", user);

            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(3600);
            response.addCookie(userCookie);

            response.sendRedirect(response.encodeRedirectURL("welcome"));
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<!DOCTYPE html>");
            response.getWriter().println("<html>");
            response.getWriter().println("<head>");
            response.getWriter().println("<title>Ошибка входа</title>");
            response.getWriter().println("</head>");
            response.getWriter().println("<body>");
            response.getWriter().println("<h2>Неверный логин или пароль</h2>");
            response.getWriter().println("<a href='login'>Попробовать снова</a>");
            response.getWriter().println("</body>");
            response.getWriter().println("</html>");
        }
    }

}