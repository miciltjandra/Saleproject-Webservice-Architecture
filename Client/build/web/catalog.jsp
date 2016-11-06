<%-- 
    Document   : catalog
    Created on : Nov 6, 2016, 3:27:17 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){%>
	
        <%= cookie.getName() %>
<%
if(cookie.getName().equals("user")) userName = cookie.getValue();}
}
//if(userName == null) response.sendRedirect("login.jsp");
%>
<%=userName%>
    </body>
</html>
