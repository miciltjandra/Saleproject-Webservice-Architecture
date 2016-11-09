<%-- 
    Document   : menubar
    Created on : Nov 9, 2016, 10:19:22 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Cookie[] cookies = request.getCookies();
    String user = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("spusername")) {
                user = cookie.getValue();
            }
         }
    }
%>
<div class="middle fix">
	<div id="hi">Hi <span class="bold">
		<%= user %>
	</span></div>
	
	<div id="logout"><a class="maroon" href="index.php">logout</a></div><br>
</div>
<ul id="tab">
<% String now = request.getServletPath(); %>
<li><a href="catalog.php"       <% if(now.equals("/catalog.jsp")) {out.println("class=\"active\"");}%> >Catalog</a></li>
<li><a href="yourproducts.php" 	 	>Your Products</a></li>
<li><a href="addproduct.php" 	 	>Add Product</a></li>
<li><a href="sales.php" 		>Sales</a></li>
<li><a href="purchases.php" 		>Purchases</a></li>
</ul>
<br />
<br />