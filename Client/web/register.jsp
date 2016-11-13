<%-- 
    Document   : register
    Created on : Nov 10, 2016, 5:41:20 PM
    Author     : NN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" href="style.css">
        <script src="Javascripts/registerscript.js"></script>
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
        <form action = "RegisterServlet" method = "post" id = "registerform"  class="text" onsubmit="return validateform()">
            <legend class="text large"> Register </legend>
            <hr>
            Full Name <br/>
            <input type = "text" class="reg_text" name = "fullname" id="name" required="required" oninput="validate(this.value, 'name')"> <br/>
            Username <br/>
            <input type = "text" class="reg_text" name = "user" id="user" required="required" oninput="validate(this.value,this.name)"> <br/>
            Email<br/>
            <input type = "text" class="reg_text" name = "email" id="email" required="required" oninput="validate(this.value, this.name)"> <br/>
            Password<br/>
            <input type = "password" class="reg_text" name = "pass" id="pass" required="required"> <br/>
            Confirm Password <br/>
            <input type = "password" class="reg_text" name = "conpass" id="pass2" required="required" oninput="confirmPassword()"> <br/>
            Full Address <br/>
            <input type = "text" class="reg_text" name = "add" id="address" required="required"> <br/>
            Postal Code <br/>
            <input type = "text" class="reg_text" name = "postal" id="postcode" maxlength="5" required="required" oninput="validate(this.value,this.name)"> <br/>
            Phone Number <br/>
            <input type = "text" class="reg_text" name = "phone" id="phone" maxlength="12" required="required" oninput="validate(this.value,this.name)"> <br/>
            
            
            <input type = "submit" class="submit" value = "Register">
        </form>
        <br/>
        <div class="text clear bold">Already have an account? Login <a href = "login.jsp" class="blue"> here </a></div>
    </body>
</html>
