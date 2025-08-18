package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import user.User;

import java.io.IOException;
import java.text.SimpleDateFormat;

//@WebServlet("/userinfo")
public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }

        Object userObject = session.getAttribute("user");
        if (userObject instanceof User) {
            User user = (User) userObject;

            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<!DOCTYPE html>");
            resp.getWriter().println("<html>");
            resp.getWriter().println("<head>");
            resp.getWriter().println("<title>Информация о пользователе</title>");
            resp.getWriter().println("</head>");
            resp.getWriter().println("<body>");
            resp.getWriter().println("<h1>Информация о пользователе</h1>");
            resp.getWriter().println("<p>Имя пользователя: " + user.getUsername() + "</p>");
            resp.getWriter().println("<p>Роль: " + user.getRole() + "</p>");
            resp.getWriter().println("<p>Время входа: " + new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(user.getLoginTime()) + "</p>");
            resp.getWriter().println("<a href='welcome'>Вернуться</a>");
            resp.getWriter().println("</body>");
            resp.getWriter().println("</html>");

        } else {
            resp.sendRedirect("login");
        }
    }
}
