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
    <div class="middle title"><span class="maroon">Sale</span><span id="project">Project</span></div>
    <body class="middle">
        <form action = "LoginServlet" method = "post" id = "loginform"  class="text">
            <input type="hidden" name="action" value="login">
            <legend class="text large"> Please Login </legend>
            <hr>
            Email or Username <br/>
            <input type = "text" class="reg_text" name = "user"> <br/>
            Password <br/>
            <input type = "password" class="reg_text" name = "pass"> <br/>
            <input type = "submit" class="submit" value = "Login">
        </form>
        <br/>
        <div class="text clear bold">Don't have an account yet? Register <a href = "register.php" class="blue"> here </a></div>
    </body>
</html>
