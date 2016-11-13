<%-- 
    Document   : purchase_form
    Created on : Nov 10, 2016, 5:42:19 PM
    Author     : NN
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchase Form</title>
        <link rel="stylesheet" href="style.css">
        <script type="text/javascript" src="Javascripts/addscript.js"></script>
    </head>
    <jsp:include page="header.html"/>
    <jsp:include page="menubar.jsp"/>    
    <body class="middle" onload="myFunction()">
        <div class="large text"> Please confirm your purchase </div>
        <br /><hr /><br />
        <form onsubmit="return validatePurch()" action="ConfirmServlet" method="post" id="addform">
        <%-- start web service invocation --%>
        <%
            java.lang.String token = "asdfasdfgh";
            java.lang.String id = "1";
            long pprice = 0;
            org.json.simple.JSONObject jsonObj = null;
            try {
                marketplace.Marketplace_Service service = new marketplace.Marketplace_Service();
                marketplace.Marketplace port = service.getMarketplacePort();
                // TODO initialize WS operation arguments here
                

                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("sptoken")) {
                            token = cookie.getValue();
//                            out.println(token);
                        }
                        if (cookie.getName().equals("spuser")) {
                            id = cookie.getValue();
                            out.println("<input type='hidden' name='buyer_id' value='" + id + "' />");
//                            out.println(id);
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

            try {
                marketplace.Marketplace_Service service = new marketplace.Marketplace_Service();
                marketplace.Marketplace port = service.getMarketplacePort();
                 // TODO initialize WS operation arguments here
                java.lang.String idproduk = request.getParameter("id");
                // TODO process result here
                marketplace.Product result = port.retrieveId(token, id, idproduk);
                out.println("Product :"+result.getProductName()+"<br>");
                out.print("Price   : IDR ");
                out.print("<span id=\"price\">" +NumberFormat.getNumberInstance(Locale.GERMAN).format(result.getPrice()) + "</span>");
                out.println("<br>");
                pprice = result.getPrice();
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
            String urlParameters  = "id=" + id;
            java.net.URL url = new java.net.URL("http://localhost:8082/IdentityService/UserInfo?" + urlParameters);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = in.readLine()) != null)
            {
                stringBuilder.append(line);
            }
            org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
            try {
                jsonObj = (org.json.simple.JSONObject) parser.parse(stringBuilder.toString());
            } catch (Exception ex) {
                
            }
    %>

        <%-- end web service invocation --%>
        
            <input type='hidden' name='buyer_id' value="">
            <span style="margin-right:14px">Quantity</span>:
            <input type="number" id="quantity" name="quantity" autocomplete="off" value="1" required min="1" maxlength="10" max="9999999999" onInput="myFunction()"/> PCS<br>
            <p id="demo" style="margin-top: 3px">Total Price: IDR </p>

            Delivery to :
            <br>
            <br>
            <input type="hidden" name="id" value="<%out.print(request.getParameter("id"));%>"/>
            Consignee <br />
            <input class="reg_text" type="text" name="consignee" required="required" value='<% out.print(jsonObj.get("fullname")); %>'/> 
            <br /><br />
            Full Address <br />
            <textarea class="reg_text" rows="4" form="addform" name="address" required="required"><% out.print(jsonObj.get("address")); %></textarea> 
            <br /><br />
            Postal Code <br />
            <input id="postal" class="reg_text" type="number" name="postal" required="required"  maxlength="5" min="0" oninput="valNumber(this.value, 'postal', 5, 3)"
                   value="<% out.print(jsonObj.get("postalcode")); %>"/> 
            <br /><br />
            Phone Number <br />
            <input id="phone" class="reg_text" type="number" name="phone" required="required" maxlength="12" min="0" oninput="valNumber(this.value, 'phone', 12, 8)"
                   value="<% out.print(jsonObj.get("phonenumber")); %>"/>
            <br /><br />
            12 Digit Credit Card Number <br />
            <input id="credit" class="reg_text" type="number" name="credit" required="required" maxlength="12" min="0" max="999999999999" oninput="valNumber(this.value, 'credit', 12, 12)"/>
            <br /><br />
            3 Digit Card Verification Value <br />
            <input id="verify" class="reg_text" type="number" name="verif" required="required" maxlength="3" min="0" max="999" oninput="valNumber(this.value, 'verify', 3, 3)"/> 
            <br /><br />

            <br />
            <div style="float:right">
                <input class="submit" type="submit" value="Confirm" name="confirm">
            </div>
        </form>

    <script>
        function myFunction() {
            var x = document.getElementById("quantity").value;
            x = x * <% out.print(pprice); %>;
            x = x.toFixed().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
            document.getElementById("demo").innerHTML = "Total Price: IDR " + x ;
        }
    </script>
    </body>
</html>
