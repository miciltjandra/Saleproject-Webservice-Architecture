/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace;

import java.sql.Timestamp;
import java.util.Date;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Asus
 */
@XmlRootElement(name = "Product")
public class Product {
    @XmlElement(name = "product_id", required=true)
    private int product_id;
    
    @XmlElement(name = "product_name", required=true)
    private String product_name;
    
    @XmlElement(name = "description", required=true)
    private String description;
    
    @XmlElement(name = "likes", required=true)
    private int likes;
    
    @XmlElement(name = "added_date", required=true)
    private Date added_date;
    
    @XmlElement(name = "price", required=true)
    private long price;
    
    @XmlElement(name = "image", required=true)
    private String image;
    
    @XmlElement(name = "seller_id", required=true)
    private int seller_id;
    
    @XmlElement(name = "username", required=true)
    private String username;
    
    @XmlElement(name = "purchases", required=true)
    private int purchases;
    
    @XmlElement(name = "status", required=true)
    private String status;
    
    public Product() {
        status = "invalid";
    }
    
    public Product(int id, String name, String desc, int like, Timestamp date, long prc, String img, int sellid, String sellname, int pur) {
        product_id = id;
        product_name = name;
        description = desc;
        likes = like;
        added_date = date;
        price = prc;
        image = img;
        seller_id = sellid;
        username = sellname;
        purchases = pur;
        status = "valid";
    }
}
