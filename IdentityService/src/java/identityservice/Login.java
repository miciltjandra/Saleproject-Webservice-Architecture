/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identityservice;

import database.IdentityDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import token.Token;

/**
 *
 * @author Asus
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {


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
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        JSONObject json = new JSONObject();
        String query = "aaa";
        if (user.contains("@")) {
            query = "SELECT user_id, password, username FROM user WHERE email = '" + user + "'";
        }
        else {
            query = "SELECT user_id, password, username FROM user WHERE username = '" + user + "'";
        }
        
        
        IdentityDB db = new IdentityDB();
        try{
            ResultSet rs = db.select(query);
            String user_id = null;
            String passw = null;
            String username = null;
            if(rs.next()) {
            //Retrieve by column name
                user_id = rs.getString("user_id");
                passw = rs.getString("password");
                username = rs.getString("username");
            }
            if(pass.equals(passw)) {
                json.put("status", "valid");
                json.put("token", Token.generateToken(user_id));
                json.put("time", "30");
                json.put("user_id", user_id);
                json.put("username", username);
            }
            else {
                json.put("status", "error");
            }
            PrintWriter out = response.getWriter();
            out.write(json.toString());
        }
        catch(SQLException e) {
            
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
