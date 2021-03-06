/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login.LoginServlet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author NN
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        
        URL url = new URL("http://localhost:8082/IdentityService/Register");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        String urlParameters  = "fullname=" + request.getParameter("fullname")
                                +"&user=" + request.getParameter("user")
                                +"&email=" + request.getParameter("email")
                                +"&pass=" + request.getParameter("pass")
                                +"&conpass=" + request.getParameter("conpass")
                                +"&add=" + request.getParameter("add")
                                +"&postal=" + request.getParameter("postal")
                                +"&phone=" + request.getParameter("phone");
        
        
        byte[] postData       = urlParameters.getBytes(StandardCharsets.UTF_8);
        int    postDataLength = postData.length;

        connection.setRequestMethod("POST");
        connection.setDoOutput( true );
        connection.setInstanceFollowRedirects( false );
        connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
        connection.setRequestProperty( "charset", "utf-8");
        connection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        connection.setUseCaches( false );
        
        
        
        try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
           wr.write( postData );
        }
        
        
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = in.readLine()) != null)
        {
            stringBuilder.append(line + "\n");
        }
        response.getWriter().println(stringBuilder.toString());

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject) parser.parse(stringBuilder.toString());
        } catch (ParseException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        String status = (String) jsonObj.get("status");
        
        
        response.getWriter().println(status);
        
        if (status.equals("valid")) {
            String token = (String) jsonObj.get("token");
            String time = (String) jsonObj.get("time");
            String user_id = (String) jsonObj.get("user_id");
            String username = (String) jsonObj.get("username");
            Cookie accesstoken = new Cookie("sptoken", token);
            Cookie expirytime = new Cookie("sptime", time);
            Cookie user = new Cookie("spuser", user_id);
            Cookie usernamecookie = new Cookie("spusername", username);
            response.addCookie(accesstoken);
            response.addCookie(expirytime);
            response.addCookie(user);
            response.addCookie(usernamecookie);
            response.sendRedirect("catalog.jsp");
        }
        else {
            response.sendRedirect("register.jsp");
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
