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
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

        String fullname= request.getParameter("fullname");
        String user= request.getParameter("user");
        String email=request.getParameter("email");
        String pass=request.getParameter("pass");
        String conpass=request.getParameter("conpass");
        String add=request.getParameter("add");
        String postal=request.getParameter("postal");
        String phone=request.getParameter("phone");
        
        
        JSONObject json = new JSONObject();
        String query = "INSERT INTO user (username, email, password, fullname, address, postalcode, phonenumber) VALUES ('"+ user +"','"+ email +"','"+ pass +"','"+fullname+"','"+add+"','"+postal+"','"+phone+"')";
        String queryread = "SELECT user_id, password, username FROM user WHERE username = '" + user + "'";
        String vquery1 = "SELECT username FROM user WHERE username = '" + user + "'";
        String vquery2 = "SELECT email FROM user WHERE email = '" + email + "'";
        
        IdentityDB db = new IdentityDB();
        try{
            ResultSet validate1 = db.select(vquery1);
            ResultSet validate2 = db.select(vquery2);
            if(validate1.next() && validate2.next()){
                json.put("status", "error");
            } else if (!pass.equals(conpass)){
                json.put("status", "error");
            } else {
                int x = db.update(query);
                ResultSet rs = db.select(queryread);
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
                } else {
                    json.put("status", "error");
                }
            }
                             
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            db.closeDB();
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
