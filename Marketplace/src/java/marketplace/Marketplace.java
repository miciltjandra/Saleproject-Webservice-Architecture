/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import database.MarketDB;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebResult;

import marketplace.Product;

/**
 *
 * @author Asus
 */
@WebService(serviceName = "Marketplace")
public class Marketplace {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkAccess")
    public boolean checkAccess(@WebParam(name = "token") String token, @WebParam(name = "id") String id) throws IOException {
        //TODO write your implementation code here:
        String urlParameters  = "token=" + token + "&id=" + id;
        URL url = new URL("http://localhost:8080/IdentityService/ValidateToken?" + urlParameters);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();


        connection.setRequestMethod("GET");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = in.readLine()) != null)
        {
            stringBuilder.append(line);
        }
        
        boolean ret = false;
        if(stringBuilder.toString().equals("valid")) {
            ret = true;
        }
        else {
            ret = false;
        }
        
        return ret;
        //return stringBuilder.toString();
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "retrieveProduct")
    @WebResult(name="Product")
    public ArrayList<Product> retrieveProduct(@WebParam(name = "token") final String token, @WebParam(name = "id") final String id, @WebParam(name = "searchtype") final String searchtype, @WebParam(name = "value") final String value) {
        //TODO write your implementation code here:
        ArrayList<Product> result = new ArrayList<Product>();
        boolean valid = false;
        
        try {
            valid = checkAccess(token, id);
        } catch (IOException ex) {
            Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (valid) {
            try {
                valid = checkAccess(token, id);
            } catch (IOException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }

            String newval = null;
            String statement = "";
            if (!searchtype.equals("base")) {
                newval = "'%" + value + "%'";
                statement = "WHERE " + searchtype + " like " + newval + "\n";
            }

            String query = "SELECT *, \n" +
                    "(SELECT sum(quantity) as q\n" +
                     "FROM purchase\n" +
                     "WHERE product_id = product_purchased) as purchases\n" +
                    "FROM product\n" +
                    statement +
                    "ORDER BY added_date desc";

            MarketDB db = new MarketDB();
            String aaa = "asdf";
            try {
                ResultSet rs = db.select(query);
                String asdf = "A";
                while (rs.next()) {
                    asdf += rs.getString("product_name");
                    result.add(new Product(rs.getInt("product_id"),
                                            rs.getString("product_name"),
                                            rs.getString("description"),
                                            rs.getInt("likes"),
                                            rs.getTimestamp("added_date"),
                                            rs.getLong("price"),
                                            rs.getString("image"),
                                            rs.getInt("seller_id"),
                                            rs.getString("username"),
                                            rs.getInt("purchases")
                                            ));
                }
                aaa = asdf;
            } catch (SQLException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            result.add(new Product());
        }
        
        
        
            
        return result;
    }


    
}
