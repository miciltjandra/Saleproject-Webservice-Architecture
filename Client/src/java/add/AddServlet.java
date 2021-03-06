/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package add;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import marketplace.FileNotFoundException_Exception;
import marketplace.Marketplace_Service;

/**
 *
 * @author Joshua A Kosasih
 */
@WebServlet(name = "AddServlet", urlPatterns = {"/AddServlet"})
@MultipartConfig
public class AddServlet extends HttpServlet {

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
        
        String nameprod = request.getParameter("name");
        String desc = request.getParameter("desc");
        String price = request.getParameter("price");
        
        
        Part filePart = request.getPart("imagefile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        
        response.getWriter().println(username + " with token " + token + " wants to add " + nameprod + " with image " + fileName);
        
        
               
        byte[] binfile;
        try {
            try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                byte[] b = new byte[4096];
                int n = 0;
                while ((n = fileContent.read(b)) != -1) {
                    output.write(b, 0, n);
                }
                binfile = output.toByteArray();
            }
        } catch (IOException e) {
            binfile = new byte[2048];
        } finally {
            if (fileContent != null) {               
                fileContent.close();               
            }
        }
        
        marketplace.Marketplace port = service.getMarketplacePort();                        
                      
        try {
            if(port.addProduct(nameprod, desc, price, token, user, username, fileName, binfile)){
                response.sendRedirect("yourproducts.jsp");
            } else {
                response.getWriter().println("Sorry your request cannot be processed");
            }
        } catch (FileNotFoundException_Exception ex) {
            Logger.getLogger(AddServlet.class.getName()).log(Level.SEVERE, null, ex);
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
