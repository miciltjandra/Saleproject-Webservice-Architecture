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
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebResult;

import marketplace.Product;
import sun.misc.IOUtils;

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
        URL url = new URL("http://localhost:8082/IdentityService/ValidateToken?" + urlParameters);
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
                db.closeDB();
            } catch (SQLException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            result.add(new Product());
        }     
        return result;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "increaseLike")
    public String increaseLike(@WebParam(name = "userid") String userid, @WebParam(name = "productid") String productid) {
        //TODO write your implementation code here:
        String like = getLiked(userid,productid);
        String op = "";
        String query1 = "";
        if (like.equals("LIKE")) {
            op = "+";
            query1 = "INSERT INTO liked(user_id, product_id)\n"+
            "VALUES("+userid+","+productid+")";
        } else if (like.equals("LIKED")) {
            op = "-";
            query1 = "DELETE FROM liked\n"+
            "WHERE user_id = "+userid+" AND product_id = "+productid;
        }
        String query2 = "UPDATE product\n"+
	"SET likes = likes"+ op +"1\n"+
	"WHERE product_id = " +  productid;
        
        String queryget = "SELECT likes FROM product WHERE product_id = " + productid;
        MarketDB db = new MarketDB();
        String likes = "a";
        try {
            db.update(query1);
            db.update(query2);
            ResultSet rs = db.select(queryget);
            if(rs.next()) {
            //Retrieve by column name
                likes = rs.getString("likes");
            }
            db.closeDB();
        } catch (SQLException ex) {
            Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        return likes;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getLiked")
    public String getLiked(@WebParam(name = "userid") String userid, @WebParam(name = "productid") String productid) {
        //TODO write your implementation code here:
        MarketDB db = new MarketDB();
        String query = "SELECT *\n" +
		"FROM liked\n" +
		"WHERE user_id=\""+ userid +"\" AND product_id=\""+ productid + "\"";
        String like = "";
        try {
            ResultSet rs = db.select(query);
            if (!rs.isBeforeFirst() ) {    
                like = "LIKE";
            } else {
                like = "LIKED";
            }
            db.closeDB();
        } catch (SQLException ex) {
            Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return like;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "retrieveSales")
    @WebResult(name="Purchase")
    public ArrayList<Purchase> retrieveSales(@WebParam(name = "token") final String token, @WebParam(name = "id") final String id, @WebParam(name = "searchtype") final String searchtype, @WebParam(name = "value") final String value) {
        //TODO write your implementation code here:
        ArrayList<Purchase> result = new ArrayList<Purchase>();
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

/*
            String query = "SELECT *, \n" +
                    "(SELECT sum(quantity) as q\n" +
                     "FROM purchase\n" +
                     "WHERE product_id = product_purchased) as purchases\n" +
                    "FROM product\n" +
                    statement +
                    "ORDER BY added_date desc";
*/

            String query = "SELECT * FROM purchase WHERE " + searchtype + " = " + id + " ORDER BY purchase_date desc ";
            
            
            MarketDB db = new MarketDB();
            String aaa = "asdf";
            try {
                ResultSet rs = db.select(query);
                String asdf = "A";
                while (rs.next()) {
                    asdf += rs.getString("product_name");
                    result.add(new Purchase(rs.getInt("purchase_id"),
                                            rs.getInt("product_purchased"),
                                            rs.getInt("quantity"),
                                            rs.getInt("buyer_id"),
                                            rs.getString("consignee"),
                                            rs.getString("deliv_address"),
                                            rs.getString("postalcode"),
                                            rs.getString("phone"),
                                            rs.getString("creditcard"),
                                            rs.getString("verification"),
                                            rs.getTimestamp("purchase_date"),
                                            rs.getString("product_name"),
                                            rs.getLong("price"),
                                            rs.getString("image"),
                                            rs.getInt("seller_id"),
                                            rs.getString("buyer_username"),
                                            rs.getString("seller_username")
                                            ));
                }
                aaa = asdf;
            } catch (SQLException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            result.add(new Purchase());
        }     
        return result;

    }

    /**
     * Web service operation
     * @param prdname
     * @param desc
     * @param price
     * @param token
     * @param id
     * @param username
     * @param image
     * @param content
     * @return 
     */
    @WebMethod(operationName = "addProduct")
    public boolean addProduct(@WebParam(name = "prdname") String prdname, @WebParam(name = "desc") String desc, @WebParam(name = "price") String price, @WebParam(name = "token") String token, @WebParam(name = "id") String id, @WebParam(name = "username") String username, @WebParam(name = "image") String image, @WebParam(name = "content") byte[] content) throws FileNotFoundException {
        boolean valid = false;
        
        try {
            valid = checkAccess(token, id);
        } catch (IOException ex) {
            Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean result = false;
        if (valid) {
            try {
                valid = checkAccess(token, id);
            } catch (IOException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
               
            // save image locally on folder
            try {
            InputStream is = new ByteArrayInputStream(content);
            OutputStream os = new FileOutputStream(new File("D:\\Tubes WBD 2\\Marketplace\\web\\Image\\" + image));
                        
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                    os.write(bytes, 0, read);
            }
            System.out.println("Done!");
            
            } catch (IOException e) {
                
            }
            
            
            
            // get date
            java.util.Date utilDate = new Date();          
            java.sql.Timestamp date = new java.sql.Timestamp(utilDate.getTime());
            
            // Sementara image blom dimasukkan, harusnya ada setelah price
            String query = "INSERT INTO product(product_name, description, added_date, price, seller_id, username, image)" +
            "VALUES('" +
            prdname + "', '" +
            desc + "', '" +
            date + "', '" +
            price + "', '" +
            id + "', '" +
            username + "', '" +
            image + "')";
            
            MarketDB db = new MarketDB();
            
            try {
                int rs = db.update(query);
                db.closeDB();
                if (rs >= 0) {
                    result = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    } 

    /**
     * Web service operation
     */
    @WebMethod(operationName = "retrieveId")
    public Product retrieveId(@WebParam(name = "token") String token, @WebParam(name = "id") String id, @WebParam(name = "idproduk") String idproduk) {
        Product result = new Product();
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
            
            String statement = "WHERE product_id = " + idproduk + "\n";
            
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
                    result = new Product(rs.getInt("product_id"),
                                            rs.getString("product_name"),
                                            rs.getString("description"),
                                            rs.getInt("likes"),
                                            rs.getTimestamp("added_date"),
                                            rs.getLong("price"),
                                            rs.getString("image"),
                                            rs.getInt("seller_id"),
                                            rs.getString("username"),
                                            rs.getInt("purchases")
                                            );
                }
                aaa = asdf;
                db.closeDB();
            } catch (SQLException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    /**
     * Web service operation
     * @param prdname
     * @param desc
     * @param price
     * @param token
     * @param id
     * @param prdid
     * @return 
     */
    @WebMethod(operationName = "editProduct")
    public boolean editProduct(@WebParam(name = "prdname") String prdname, @WebParam(name = "desc") String desc, @WebParam(name = "price") String price, @WebParam(name = "token") String token, @WebParam(name = "id") String id, @WebParam(name = "prdid") String prdid) {
        //TODO write your implementation code here:
        boolean valid = false;
        
        try {
            valid = checkAccess(token, id);
        } catch (IOException ex) {
            Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean result = false;
        if (valid) {
            try {
                valid = checkAccess(token, id);
            } catch (IOException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Sementara image blom dimasukkan, harusnya ada setelah price
            String query = "UPDATE product " +                    
            "SET product_name='" + prdname +
            "'\n, description='" + desc +           
            "'\n, price='" + price +
            "'\n WHERE product_id=" + prdid + ";";    
            
            MarketDB db = new MarketDB();
            
            try {
                int rs = db.update(query);
                db.closeDB();
                if (rs >= 0) {
                    result = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    /**
     * Web service operation
     * @param token
     * @param id
     * @param prdid
     * @return 
     */
    @WebMethod(operationName = "deleteProduct")
    public boolean deleteProduct(@WebParam(name = "token") String token, @WebParam(name = "id") String id, @WebParam(name = "prdid") String prdid) {
        //TODO write your implementation code here:
        //TODO write your implementation code here:
        boolean valid = false;
        
        try {
            valid = checkAccess(token, id);
        } catch (IOException ex) {
            Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean result = false;
        if (valid) {
            try {
                valid = checkAccess(token, id);
            } catch (IOException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Sementara image blom dimasukkan, harusnya ada setelah price
            String query = "DELETE FROM product " +                                
            "\n WHERE product_id=" + prdid + ";";
            
            MarketDB db = new MarketDB();
            
            try {
                int rs = db.update(query);
                db.closeDB();
                if (rs >= 0) {
                    result = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    /**
     * Web service operation
     * @param prdname
     * @param desc
     * @param price
     * @param token
     * @param id
     * @param username
     * @return 
     */
    @WebMethod(operationName = "confirmPurchase")
    public Boolean confirmPurchase(@WebParam(name = "prdname") String prdname, @WebParam(name = "desc") String desc, @WebParam(name = "price") String price, @WebParam(name = "token") String token, @WebParam(name = "id") String id, @WebParam(name = "username") String username) {
        boolean valid = false;
        
        try {
            valid = checkAccess(token, id);
        } catch (IOException ex) {
            Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean result = false;
        if (valid) {
            try {
                valid = checkAccess(token, id);
            } catch (IOException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }

            // get date
            java.util.Date utilDate = new Date();          
            java.sql.Timestamp date = new java.sql.Timestamp(utilDate.getTime());
            
            String query = "INSERT INTO purchase (`product_purchased`, `quantity`, `buyer_id`, `consignee`, `deliv_address`, `postalcode`, `phone`, `creditcard`, `verification`, `purchase_date`, `product_name`, `price`, `seller_id`, `image`, `buyer_username`, `seller_username`) \n"
                    + "VALUES ("
                    + "'15', "
                    + "'1', "
                    + "'12', "
                    + "'fda', "
                    + "'ada', "
                    + "'22222', "
                    + "'312312313', "
                    + "'31232133123', "
                    + "'123', "
                    + "'2016-09-01 12:12:12', "
                    + "(SELECT product_name FROM product WHERE product_id = 15) , "
                    + "(SELECT price FROM product WHERE product_id = 15), "
                    + "(SELECT seller_id FROM product WHERE product_id = 15), "
                    + "(SELECT image FROM product WHERE product_id = 15), "
                    + "(SELECT username FROM user WHERE user_id = 12), "
                    + "(SELECT username FROM product WHERE product_id = 15));";
            
            MarketDB db = new MarketDB();
            
            try {
                int rs = db.update(query);
                db.closeDB();
                if (rs >= 0) {
                    result = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    
    /**
     * Web service operation
     * @param token
     * @param id
     * @param id_prod
     * @param quantity
     * @param buyer_id
     * @param consignee
     * @param address
     * @param postal
     * @param phone
     * @param credit
     * @param verif
     * @return 
     */
    @WebMethod(operationName = "confirmPurchase")
    public Boolean confirmPurchase(@WebParam(name = "token") String token, @WebParam(name = "id") String id, @WebParam(name = "id_prod") String id_prod, @WebParam(name = "quantity") String quantity, @WebParam(name = "buyer_id") String buyer_id, @WebParam(name = "consignee") String consignee, @WebParam(name = "address") String address, @WebParam(name = "postal") String postal, @WebParam(name = "phone") String phone, @WebParam(name = "credit") String credit, @WebParam(name = "verif") String verif) {
        boolean valid = false;
        
        try {
            valid = checkAccess(token, id);
        } catch (IOException ex) {
            Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean result = false;
        if (valid) {
            try {
                valid = checkAccess(token, id);
            } catch (IOException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }

            // get date
            java.util.Date utilDate = new Date();          
            java.sql.Timestamp date = new java.sql.Timestamp(utilDate.getTime());
            
            String query = "INSERT INTO purchase (`product_purchased`, `quantity`, `buyer_id`, `consignee`, `deliv_address`, `postalcode`, `phone`, `creditcard`, `verification`, `purchase_date`, `product_name`, `price`, `seller_id`, `image`, `buyer_username`, `seller_username`) \n"
                    + "VALUES ('"
                    + id_prod + "','"
                    + quantity + "','"
                    + buyer_id + "','"
                    + consignee + "','"
                    + address + "','"
                    + postal + "','"
                    + phone + "','"
                    + credit + "','"
                    + verif + "','"
                    + "'2016-09-01 12:12:12', "
                    + "(SELECT product_name FROM product WHERE product_id = "+ id_prod +") , "
                    + "(SELECT price FROM product WHERE product_id = "+ id_prod +"), "
                    + "(SELECT seller_id FROM product WHERE product_id = "+ id_prod +"), "
                    + "(SELECT image FROM product WHERE product_id = "+ id_prod +"), "
                    + "(SELECT username FROM user WHERE user_id = "+ buyer_id +"), "
                    + "(SELECT username FROM product WHERE product_id = "+ id_prod +"));";
            
            MarketDB db = new MarketDB();
            
            try {
                int rs = db.update(query);
                db.closeDB();
                if (rs >= 0) {
                    result = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Marketplace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "formatDate")
    public String formatDate(@WebParam(name = "time") Date time) {
        SimpleDateFormat ft = 
        new SimpleDateFormat ("EEEE, dd MMMM YYYY");
        return ft.format(time);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "formatTime")
    public String formatTime(@WebParam(name = "time") Date time) {
        SimpleDateFormat ft = 
        new SimpleDateFormat ("hh.mm");
        return ft.format(time);
    }
}
