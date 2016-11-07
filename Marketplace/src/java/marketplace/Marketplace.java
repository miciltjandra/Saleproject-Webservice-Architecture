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
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "retrieveProduct")
    @WebResult(name="Product")
    public ArrayList<Product> retrieveProduct(@WebParam(name = "searchtype") final String searchtype, @WebParam(name = "value") final String value) {
        //TODO write your implementation code here:
        ArrayList<Product> result = new ArrayList<Product>();
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
        
            
        return result;
    }
    
    
}
