<%-- 
    Document   : catalog
    Created on : Nov 6, 2016, 3:27:17 PM
    Author     : Asus
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title> Catalog </title>
		<link rel="stylesheet" href="style.css">
		<script src="Javascripts/catalogscript.js"></script>
	</head>
        <jsp:include page="header.html"/>
        <jsp:include page="menubar.jsp"/>
	<body class="middle">		
            
            <h1> What are you going to buy today? </h1>
            <hr/>
            <form id="searchbox" action="#" method="post">
            <input type="text" name="search" id="search" placeholder="Search catalog ...">
            <input type="submit" class="submit" id="submitsearch" name="submit_search" value="   GO   "> <br/>
            by
            <label class="searchradio"><input type="radio" name="searchcategory" value="product_name" checked="checked"> product </label><br/>
            <label class="searchradio"><input type="radio" name="searchcategory" value="username"> store </label>
            </form>
            <br/>
                <%-- start web service invocation --%><hr/>
                <%
                    try {
                        marketplace.Marketplace_Service service = new marketplace.Marketplace_Service();
                        marketplace.Marketplace port = service.getMarketplacePort();
                        // TODO initialize WS operation arguments here
                        java.lang.String token = "asdfasdfgh";
                        java.lang.String id = "1";
                        
                        Cookie[] cookies = request.getCookies();
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
                        
                        //get cookie
                         
                        java.lang.String searchtype = "";
                        java.lang.String value = "";
                        if (request.getParameter("submit_search") != null) {
                            searchtype = request.getParameter("searchcategory");
                            value = request.getParameter("search");
                        } else {
                            searchtype = "base";
                        }
                        
                        // TODO process result here
                        java.util.List<marketplace.Product> result = port.retrieveProduct(token, id, searchtype, value);
                        if (result.get(0).getStatus().equals("invalid")) {
                            response.sendRedirect("/Client/LogoutServlet");
                        }
                        else {
                            String imgsrc = "http://localhost:8082/Marketplace/Image/";
                            for (marketplace.Product product : result) {
                                out.println("<div class=\"product\">");
                                out.println("<div class=\"bold\">" + product.getUsername() + "</div>");
                                out.println("<div>added this on " + port.formatDate(product.getAddedDate()));
                                out.println(", at "+ port.formatTime(product.getAddedDate())+"</div>");
                                out.println("<hr/>");
                                out.println("<div class=\"catalogleft\">");
                                out.println("<img class=\"icon\" src=\"" + imgsrc + product.getImage() + "\" alt=\""+ product.getProductName() +"\"/> <br/>");
                                out.println("</div>");
                                out.println("<div class=\"catalogmid\">");
                                out.println("<div class=\"name\">" + product.getProductName() + "</div>");
                                out.println("<div class=\"price\"> IDR " + NumberFormat.getNumberInstance(Locale.GERMAN).format(product.getPrice()) + "</div>");
                                out.println("<div class=\"desc\">" + product.getDescription() + "</div>");
                                out.println("</div>");
                                out.println("<div class=\"catalogright\">");
                                out.println("<span id=\""+product.getProductId()+"_like\">" + product.getLikes() + "</span> likes <br/>");
                                out.println(product.getPurchases() + " purchases<br/><br/>");

                                //getliked!!
                                String like = port.getLiked(id,Integer.toString(product.getProductId()));
                                out.print("<a class=\"likebut");
                                if (like.equals("LIKED")) {out.print(" liked");}
                                out.print("\" id=\"" + product.getProductId() + "_likebut\" ");
                                out.println(" onclick=\"increaseLike("+product.getProductId()+","+id+")\">"+like+"</a>");

                                //onclick increase like!!

                                out.println("<a class=\"buybut\"href=\"confirm_purchase.jsp?product=" + product.getProductName() + "&id=" + product.getProductId() + "\"> BUY </a>");
                                out.println("</div>");
                                out.println("<div class=\"clear\">");
                                out.println("<hr/>\n<br/>");
                                out.println("</div>");
                                out.println("</div>\n");
                            }
                        }
                    } catch (Exception ex) {
                        // TODO handle custom exceptions here
                    }
                %>
                <%-- end web service invocation --%><hr/>

	</body>
</html>
