<%-- 
    Document   : purchases
    Created on : Nov 10, 2016, 5:40:49 PM
    Author     : NN
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchases</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <jsp:include page="header.html"/>
    <jsp:include page="menubar.jsp"/>
    <body class="middle">
        <h1> Here are your purchases </h1>
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
                
                java.lang.String searchtype = "buyer_id";
                java.lang.String value = "dummy";

                // TODO process result here
                java.util.List<marketplace.Purchase> result = port.retrieveSales(token, id, searchtype, value);
                if (result.get(0).getStatus().equals("invalid")) {
                    response.sendRedirect("login.jsp");
                } else {
                    for (marketplace.Purchase purchase : result) {
                        out.println("<div class=\"purchase\">");
                        out.println("<div class=\"bold\">" + port.formatDate(purchase.getPurchaseDate()) + "</div>");
                        out.println("<div>" + port.formatTime(purchase.getPurchaseDate()) + "</div>");
                        out.println("<hr/>");
                        out.println("<div class=\"left\">");
                        out.println("<img class=\"icon\" src=\"" + purchase.getImage() + "\" alt=\"" + purchase.getProductName() + "\"/> <br/>");
                        out.println("</div>");
                        out.println("<div class=\"mid\">");
                        out.println("<div class=\"name\">" + purchase.getProductName() + "</div>");
                        
                        out.println("<div class=\"detail\">IDR "+ NumberFormat.getNumberInstance(Locale.GERMAN).format(purchase.getQuantity() * purchase.getPrice()) + "<br/>\n");
                        out.println(purchase.getQuantity() + " pcs<br/>\n");
                        out.println("@IDR " + NumberFormat.getNumberInstance(Locale.GERMAN).format(purchase.getPrice())+ "</div><br/>\n");
                        out.println("bought from <span class='bold'>"+purchase.getSellerUsername() + " </span> <br/>\n");
                        out.println("</div><div class=\"right\">");
                        
                        out.println("Delivery to <span class=\"bold\">" + purchase.getConsignee() + "</span><br>");
                        out.println(purchase.getDelivAddress() + "<br>");
                        out.println(purchase.getPostalcode() + "<br>");
                        out.println(purchase.getPhone() + "<br>");
                        out.println("</div>");
                        out.println("</div>\n");
                        out.println("<br/>\n<br/>\n");
                    }
                }
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
        <%-- end web service invocation --%><hr/>


    </body>
</html>
