<%-- 
    Document   : login
    Created on : Nov 6, 2016, 10:51:10 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <jsp:include page="header.html"/>
    <body class="middle">
        <%
            Cookie[] cookies = request.getCookies();
            String token = "";
            String id = "";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("sptoken")) {
                        token = cookie.getValue();
                    }
                    if (cookie.getName().equals("spuser")) {
                        id = cookie.getValue();
                    }
                 }
            }

            try {
                marketplace.Marketplace_Service service = new marketplace.Marketplace_Service();
                marketplace.Marketplace port = service.getMarketplacePort();
                boolean result = port.checkAccess(token, id);
                if (result) {
                    response.sendRedirect("catalog.jsp");
                }
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
        <form action = "LoginServlet" method = "post" id = "loginform"  class="text">
            <legend class="text large"> Please Login </legend>
            <hr>
            Email or Username <br/>
            <input type = "text" class="reg_text" name = "user"> <br/>
            Password <br/>
            <input type = "password" class="reg_text" name = "pass"> <br/>
            <input type = "submit" class="submit" value = "Login">
        </form>
        <br/>
        <div class="text clear bold">Don't have an account yet? Register <a href = "register.jsp" class="blue"> here </a></div>
    </body>
</html>
