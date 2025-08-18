package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import user.User;

import java.io.IOException;

//@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        Cookie[] cookies = req.getCookies();
        String userFromCookie = null;

        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("username".equals(cookie.getName())){
                    userFromCookie = cookie.getValue();
                    break;
                }
            }
        }

        if (session == null || session.getAttribute("user") == null){
            resp.sendRedirect("login");
            return;
        }

        User user = (User) session.getAttribute("user");

            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<!DOCTYPE html>");
            resp.getWriter().println("<html>");
            resp.getWriter().println("<head>");
            resp.getWriter().println("<title>Добро пожаловать</title>");
            resp.getWriter().println("</head>");
            resp.getWriter().println("<body>");
            resp.getWriter().println("<h1>Добро пожаловать, " + user.getUsername() + "!</h1>");
            resp.getWriter().println("<p>Ваша роль: " + user.getRole() + "</p>");
            if (userFromCookie != null) {
                resp.getWriter().println("<p>Имя пользователя из cookie: " + userFromCookie + "</p>");
            }
            resp.getWriter().println("<a href='userinfo'>Посмотреть информацию о пользователе</a>");
            resp.getWriter().println("<br>");
            resp.getWriter().println("<a href='logout'>Выйти</a>");
            resp.getWriter().println("</body>");
            resp.getWriter().println("</html>");
        }

}

