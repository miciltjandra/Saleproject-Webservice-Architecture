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
import org.json.simple.JSONObject;
import token.Token;

/**
 *
 * @author Asus
 */
@WebServlet(name = "UserInfo", urlPatterns = {"/UserInfo"})
public class UserInfo extends HttpServlet {

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
        String id = request.getParameter("id");
        JSONObject json = new JSONObject();
        String query = "SELECT * FROM user WHERE user_id = " + id;
        
 
        IdentityDB db = new IdentityDB();
        try {
            ResultSet rs = db.select(query);
            if(rs.next()) {
            //Retrieve by column name
                String fullname;
                String address;
                String postalcode;
                String phonenumber;
                fullname = rs.getString("fullname");
                address = rs.getString("address");
                postalcode = rs.getString("postalcode");
                phonenumber = rs.getString("phonenumber");
                json.put("fullname", fullname);
                json.put("address", address);
                json.put("postalcode", postalcode);
                json.put("phonenumber", phonenumber);
                out.write(json.toString());
            }
            db.closeDB();
        } catch (SQLException ex) {
            Logger.getLogger(UserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
