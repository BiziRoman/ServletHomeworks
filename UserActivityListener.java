package org.example;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import user.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserActivityListener implements HttpSessionListener, HttpSessionAttributeListener {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        String message = String.format(
                "SESSION_CREATED | Session ID: %s | Time: %s",
                se.getSession().getId(),
                dateFormat.format(new Date())
        );
        context.log(message);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        String message = String.format(
                "SESSION_DESTROYED | Session ID: %s | Time %s",
                se.getSession().getId(),
                dateFormat.format(new Date())
        );
        context.log(message);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if ("user".equals(event.getName())){
            ServletContext context = event.getSession().getServletContext();
            User user = (User) event.getValue();
            String message = String.format(
                    "USER_LOGGED_IN | User: %s | Session ID: %s | Time: %s",
                    user.getUsername(),
                    event.getSession().getId(),
                    dateFormat.format(new Date())
            );
            context.log(message);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if ("user".equals(event.getName())){
            ServletContext context = event.getSession().getServletContext();
            User user = (User) event.getValue();
            String message = String.format(
                    "USER_LOGGED_OUT | User: %s | Session ID: %s | Time: %s",
                    user.getUsername(),
                    event.getSession().getId(),
                    dateFormat.format(new Date())
            );
            context.log(message);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
