<%-- 
    Document   : sales
    Created on : Nov 10, 2016, 5:40:26 PM
    Author     : NN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sales</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <jsp:include page="header.html"/>
    <jsp:include page="menubar.jsp"/>
    <body class="middle">

        <h1> Here are your Sales </h1>
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
                            out.println(token);
                        }
                        if (cookie.getName().equals("spuser")) {
                            id = cookie.getValue();
                            out.println(id);
                        }
                    }
                }
                
                java.lang.String searchtype = "seller_id";
                java.lang.String value = "dummy";

                // TODO process result here
                java.util.List<marketplace.Purchase> result = port.retrieveSales(token, id, searchtype, value);
                if (result.get(0).getStatus().equals("invalid")) {
                    response.sendRedirect("login.jsp");
                } else {
                    for (marketplace.Purchase purchase : result) {
                        out.println("<div class=\"product\">");
                        out.println("<div class=\"bold\">" + purchase.getPurchaseDate() + "</div>");
                        out.println("<hr/>");
                        out.println("<div class=\"catalogleft\">");
                        out.println("<img class=\"icon\" src=\"" + purchase.getImage() + "\" alt=\"" + purchase.getProductName() + "\"/> <br/>");
                        out.println("</div>");
                        out.println("<div class=\"catalogmid\">");
                        out.println("<div class=\"bold\">" + purchase.getProductName() + "</div>");
                        out.println("<div class=\"price\"> IDR " + purchase.getPrice() + "</div>");
                        out.println("</div>");
                        out.println("<div class=\"catalogright\">");
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
