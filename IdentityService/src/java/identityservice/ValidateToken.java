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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import token.Token;
/**
 *
 * @author Asus
 */
@WebServlet(name = "ValidateToken", urlPatterns = {"/ValidateToken"})
public class ValidateToken extends HttpServlet {


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
        PrintWriter out = response.getWriter();
        String token = request.getParameter("token");
        String id = request.getParameter("id");
        String result = null;
        //out.write(token+id);
        
        IdentityDB db = new IdentityDB();
        String query = "SELECT user_id, TIMESTAMPDIFF(MINUTE, time, NOW()) as diff\n" +
                "FROM accesstoken\n" +
                "WHERE token = '" + token + "'";
        
        try {
            ResultSet rs = db.select(query);
            String user_id = null;
            int diff = 0;
            if(rs.next()) {
            //Retrieve by column name
                user_id = rs.getString("user_id");
                diff = rs.getInt("diff");
                if (user_id.equals(id)) {
                    if (diff <= 10) {
                        result = "valid";
                        Token.extendToken(token);
                    }
                    else {
                        result = "expired";
                    }
                }
                else {
                    result = "wrong user";
                }
            }
            else {
                result = "no access token";
            }
            db.closeDB();
        } catch (SQLException ex) {
            Logger.getLogger(ValidateToken.class.getName()).log(Level.SEVERE, null, ex);
            result = "error";
        }
        
        
        out.write(result);
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
