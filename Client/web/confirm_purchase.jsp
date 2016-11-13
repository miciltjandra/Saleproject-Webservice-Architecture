<%-- 
    Document   : purchase_form
    Created on : Nov 10, 2016, 5:42:19 PM
    Author     : NN
--%>

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
    <body class="middle">
        <div class="large text"> Please confirm your purchase </div>
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
                }

            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
        <%-- end web service invocation --%>

        <form onsubmit="return validatePurch()" action="ConfirmServlet" method="post" id="addform" enctype="multipart/form-data">
			<span style="margin-right:14px">Quantity</span>:
			<input type="number" id="quantity" name="quantity" autocomplete="off" value="1" onInput="myFunction()" required min="1" maxlength="10" max="9999999999"/> PCS<br>
			<p id="demo" style="margin-top: 3px">Total Price: IDR <% out.println(3 * 5);%></p>
		
			<script>
			function myFunction() {
				var x = document.getElementById("quantity").value;
				x = x * <?php echo $var ?>;
				x = x.toFixed().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
				document.getElementById("demo").innerHTML = "Total Price: IDR " + x ;
			}
			</script>
			Delivery to :
			<br>
			<br>

			Consignee <br />
			<input class="reg_text" type="text" name="consignee" value="" required="required"/> 
			<br /><br />
			Full Address <br />
			<textarea class="reg_text" rows="4" form="addform" name="deliv_address" required="required" ></textarea> 
			<br /><br />
			Postal Code <br />
			<input id="postal" class="reg_text" type="number" name="postal" value="" required="required"  maxlength="5" min="0" oninput="valNumber(this.value, 'postal', 5, 3)"/> 
			<br /><br />
			Phone Number <br />
			<input id="phone" class="reg_text" type="number" name="phone" value="" required="required" maxlength="12" min="0" oninput="valNumber(this.value, 'phone', 12, 8)"/>
			<br /><br />
			12 Digit Credit Card Number <br />
			<input id="credit" class="reg_text" type="number" name="credit" required="required" maxlength="12" min="0" max="999999999999" oninput="valNumber(this.value, 'credit', 12, 12)"/>
			<br /><br />
			3 Digit Card Verification Value <br />
			<input id="verify" class="reg_text" type="number" name="verification" required="required" maxlength="3" min="0" max="999" oninput="valNumber(this.value, 'verify', 3, 3)"/> 
			<br /><br />
			
			<br />
			<div style="float:right">
				<input class="submit" type="submit" value="Confirm" name="confirm">
			</div>
		</form>
        
    </body>
</html>
