<%-- 
    Document   : addproduct
    Created on : Nov 11, 2016, 5:02:32 PM
    Author     : Joshua A Kosasih
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Add Product </title>
        <link rel="stylesheet" href="style.css">
        <script type="text/javascript" src="Javascripts/addscript.js"></script>
    </head>
    <jsp:include page="header.html"/>
    <jsp:include page="menubar.jsp"/>
    <body class="middle">
        <div class="large text"> Please add your product here </div>
        <br /><hr /><br />
        <%-- start web service invocation --%>
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

                // TODO process result here
                boolean result = port.checkAccess(token, id);
                if (!result) {
                    response.sendRedirect("login.jsp");
                }

            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
        <%-- end web service invocation --%>

        <form onsubmit="return validateAdd()" class="text" action="AddServlet" method="post" id="addform" enctype="multipart/form-data">
            Name <br />
            <input id="add_name" class="reg_text" type="text" name="name" oninput="valNotEmpty(this.value, 'add_name')" required maxlength="100"/>
            <br />
            Description (max 200 chars) <br />
            <textarea id="add_desc" class="reg_text" rows="4" form="addform" name="desc" oninput="valNotEmpty(this.value, 'add_desc')" required maxlength="200"></textarea>
            <br />
            Price (IDR) <br />
            <input id="add_price" class="reg_text" type="number" name="price" oninput="valNumber(this.value, 'add_price', 15, 1)" required maxlength="15" min="0" max="999999999999999"/> 
            <br />
            Photo <br />
            <input type="file" name="imagefile" accept="image/*"  /> 
            <br />
            <input class="submit" type="button" value="Cancel" name="cancelbtn" onclick="window.location.reload(false)" />
            <input class="submit" type="submit" value="Add" name="addbtn" />
        </form>
        <br class="breaker"/>
    </body>
</html>
