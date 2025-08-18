<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
        }
        h1 {
            color: #333;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
        }
        a {
            text-decoration: none;
            color: #007bff;
            margin: 10px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to web application</h1>

        <%
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
        %>
            <p>You authorised as: <%= session.getAttribute("user") %></p>
            <a href="userinfo">Watch user info</a>
            <a href="logout">Logout</a>
        <%
            } else {
        %>
            <p>You need to sign in to continue</p>
            <a href="login">Sign in</a>
        <%
            }
        %>
    </div>
</body>
</html>
