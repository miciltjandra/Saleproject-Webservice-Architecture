/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edit;

import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Joshua A Kosasih
 */
@WebServlet(name = "EditServlet", urlPatterns = {"/EditServlet"})
public class EditServlet extends HttpServlet {
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/Marketplace/Marketplace.wsdl")
    private Marketplace_Service service;
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        String prdid = request.getParameter("id");
        
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("spuser")) {
                    user = cookie.getValue();
                }
                if (cookie.getName().equals("sptoken")) {
                    token = cookie.getValue();
                }
            }
        }
        
        String nameprod = request.getParameter("name");
        String desc = request.getParameter("desc");
        String price = request.getParameter("price");
        
        marketplace.Marketplace port = service.getMarketplacePort();                        
                      
        if (port.editProduct(nameprod, desc, price, token, user, prdid)) {
            response.sendRedirect("yourproducts.jsp");
        } else {
            response.getWriter().println("Sorry your request cannot be processed");
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
