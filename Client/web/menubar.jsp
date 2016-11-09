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
<li><a href="catalog.jsp"       <% if(now.equals("/catalog.jsp")) {out.println("class=\"active\"");}%>  >Catalog</a></li>
<li><a href="yourproducts.jsp"  <% if(now.equals("/yourproducts.jsp")) {out.println("class=\"active\"");}%>  >Your Products</a></li>
<li><a href="addproduct.jsp" 	<% if(now.equals("/addproduct.jsp")) {out.println("class=\"active\"");}%>  >Add Product</a></li>
<li><a href="sales.jsp" 	<% if(now.equals("/sales.jsp")) {out.println("class=\"active\"");}%>	>Sales</a></li>
<li><a href="purchases.jsp" 	<% if(now.equals("/purchases.jsp")) {out.println("class=\"active\"");}%>	>Purchases</a></li>
</ul>
<br />
<br />