<%-- 
    Document   : yourproduct
    Created on : Nov 11, 2016, 4:19:39 PM
    Author     : Joshua A Kosasih
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
            <title> Your Products </title>
            <link rel="stylesheet" href="style.css">
            <script>
                function deleteP(namaP, u_id, p_id) {
                    if (confirm("Delete product \"" + namaP + "\" from SaleProject?") == true) {
                        window.location = "actionyproducts.php?id_active=" + u_id + "&product=" + p_id;
                    }
                }
            </script>
    </head>
    <jsp:include page="header.html"/>
    <jsp:include page="menubar.jsp"/>
    <body class="middle">		

        <h1> What are you going to sell today? </h1>
        <hr/>

        <br/>
        <%-- start web service invocation --%><hr/>
        <%
            try {
                marketplace.Marketplace_Service service = new marketplace.Marketplace_Service();
                marketplace.Marketplace port = service.getMarketplacePort();
                // TODO initialize WS operation arguments here
                java.lang.String token = "";
                java.lang.String id = "1";
                java.lang.String username = "";

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
                        if (cookie.getName().equals("spusername")) {
                            username = cookie.getValue();
                            out.println(id);
                        }
                    }
                }

                //get cookie


                // TODO process result here
                java.util.List<marketplace.Product> result = port.retrieveProduct(token, id, "username", username);
                if (result.get(0).getStatus().equals("invalid")) {
                    response.sendRedirect("login.jsp");
                }
                else {
                    String imgsrc = "http://localhost:8082/Marketplace/Image/";
                    for (marketplace.Product product : result) {
                        out.println("<div class=\"product\">");
                        out.println("<div class=\"bold\">" + product.getUsername() + "</div>");
                        out.println("<div>added this on " + product.getAddedDate() + "</div>");
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

                        //edit

                        out.print("<a class=\"editbut");                                
                        out.print("\" id=\"" + product.getProductId() + "_likebut\" ");
                        out.println(" href=\"editproduct.jsp?id=" + product.getProductId() + "\">EDIT</a>");

                        //delete

                        out.println("<a class=\"delbut\"href=\"/Client/DeleteServlet?id="+product.getProductId()+"\" onclick=\"deleteP('"+product.getProductName()+ "',"+ id + ", " + product.getProductId() + ")\"> DELETE </a>");
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
