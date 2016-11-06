/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identityservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.SecureRandom;
import java.math.BigInteger;

import database.IdentityDB;

/**
 *
 * @author Asus
 */
@WebServlet(name = "IdentityService", urlPatterns = {"/IdentityService"})
public class IdentityService extends HttpServlet {
    
    public String generateToken(int user_id) {
        SecureRandom random = new SecureRandom();
        String token = new BigInteger(50, random).toString(32);
        
        IdentityDB db = new IdentityDB();
        String sql = "INSERT INTO accesstoken VALUES('"+token+"', now(),'"+user_id+"')";
        try{
            db.update(sql);
        }
        catch(SQLException e) {
            
        }
        
        return sql;
    }
    
    public void extendToken(String token) {
        
    }
    
    public String validateToken(String token, int user_id) {
        return "stub";
    }
    
    public boolean login(HttpServletRequest request) {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        if ((user.equals("asdf")) && (pass.equals("asdf")))
        return true;
        else
        return false;
    }
    
    public boolean register(HttpServletRequest request) {
        return true;
    }
    
    public boolean logout() {
        return true;
    }
    
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        PrintWriter out = response.getWriter();
        out.println("tis is get");
        /*IdentityDB db = new IdentityDB();
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Database Result";
        String docType =
          "<!doctype html public \"-//w3c//dtd html 4.0 " +
           "transitional//en\">\n";
        out.println(docType +
           "<html>\n" +
           "<head><title>" + title + "</title></head>\n" +
           "<body bgcolor=\"#f0f0f0\">\n" +
           "<h1 align=\"center\">" + title + "</h1>\n");
        // Execute SQL query
        try {
            String sql;
            sql = "SELECT * FROM accesstoken";
            ResultSet rs = db.select(sql);
            out.println(sql + "<br/>");
            String a = generateToken(1);
            out.println(a + "<br/>");

            // Extract data from result set
            while(rs.next()){
               //Retrieve by column name
               String token = rs.getString("token");
               String time = rs.getString("time");

               //Display values
               out.println("Token: " + token + "<br>");
               out.println("Time: " + time + "<br>");
            }
            out.println("</body></html>");
        } catch(Exception e) {
            
        }*/
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
        PrintWriter out = response.getWriter();
        out.println(request.getParameter("user"));
        
        
        
        String action = request.getParameter("action");
        if(action.equals("login")) {
            if(login(request)) {
                out.println("valid");
            }
            else {
                out.println("invalid");
            }
        }else if(action.equals("register")) {
            register(request);
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
