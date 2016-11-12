<%-- 
    Document   : editproduct
    Created on : Nov 12, 2016, 5:02:32 PM
    Author     : Joshua A Kosasih
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Edit Product </title>
        <link rel="stylesheet" href="style.css">
        <script type="text/javascript" src="Javascripts/addscript.js"></script>
    </head>
    <jsp:include page="header.html"/>
    <jsp:include page="menubar.jsp"/>
    <body class="middle">
        <div class="large text"> Please edit your product here </div>
        <br /><hr /><br />
        <%-- start web service invocation --%>
        <%
            marketplace.Product product = new marketplace.Product();
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

                // TODO process result here
                boolean result = port.checkAccess(token, id);
                if (!result) {
                    response.sendRedirect("login.jsp");
                } else {
                    String prdid = request.getParameter("id");                    
                    product = port.retrieveId(token, id, prdid);                      
                }

            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
            
            
            
        %>
        <%-- end web service invocation --%>

        <form onsubmit="return validateAdd()" class="text" action="EditServlet" method="post" id="addform" >
            Name <br />
            <input id="add_name" value="<%=product.getProductName()%>" class="reg_text" type="text" name="name" oninput="valNotEmpty(this.value, 'add_name')" required maxlength="100"/>
            <br />
            Description (max 200 chars) <br />
            <textarea id="add_desc" class="reg_text" rows="4" form="addform" name="desc" oninput="valNotEmpty(this.value, 'add_desc')" required maxlength="200"><%=product.getDescription()%></textarea>
            <br />
            Price (IDR) <br />
            <input id="add_price" value="<%=product.getPrice()%>" class="reg_text" type="number" name="price" oninput="valNumber(this.value, 'add_price', 15, 1)" required maxlength="15" min="0" max="999999999999999"/> 
            <br />
            Photo <br />
            <input type="file" name="imagefile" accept="image/*" disabled /> 
            <br />
            <input class="submit" type="button" value="Cancel" name="cancelbtn" onclick="window.location.reload(false)" />
            <input class="submit" type="submit" value="Update" name="updatebtn" />
        </form>
        <br class="breaker"/>
    </body>
</html>
