/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package confirm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import marketplace.Marketplace_Service;

/**
 *
 * @author NN
 */
@WebServlet(name = "ConfirmServlet", urlPatterns = {"/ConfirmServlet"})
public class ConfirmServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/Marketplace/Marketplace.wsdl")
    private Marketplace_Service service;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        String user = "";
        String token = "";
        String username = "";
        
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("spuser")) {
                    user = cookie.getValue();
                }
                if (cookie.getName().equals("sptoken")) {
                    token = cookie.getValue();
                }
                if (cookie.getName().equals("spusername")) {
                    username = cookie.getValue();
                }
            }
        }
        
        String idprod = request.getParameter("id");
        String quantity = request.getParameter("quantity");
        String buyer_id = user;
        String consignee = request.getParameter("consignee");
        String address = request.getParameter("address");
        String postal = request.getParameter("postal");
        String phone = request.getParameter("phone");
        String credit = request.getParameter("credit");
        String verif = request.getParameter("verif");
        marketplace.Marketplace port = service.getMarketplacePort();                        
                      
        if(port.confirmPurchase(token, user, idprod, quantity, buyer_id, consignee, address, postal, phone, credit, verif, username)){
            response.sendRedirect("purchases.jsp");
        } else {
            response.getWriter().println("Sorry your request cannot be processed");
            response.getWriter().println(idprod+" "+quantity+" "+buyer_id+" "+consignee+" "+address+" "+postal+" "+phone+" "+credit+" "+verif);
            java.util.Date utilDate = new Date();          
            java.sql.Timestamp date = new java.sql.Timestamp(utilDate.getTime());
            response.getWriter().println(date);
        
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
