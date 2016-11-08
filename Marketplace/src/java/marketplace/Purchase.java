/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketplace;

import java.sql.Timestamp;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@XmlRootElement(name = "Purchase")
public class Purchase {
    @XmlElement(name = "purchase_id", required=true)
    private int purchase_id;
    
    @XmlElement(name = "product_purchased", required=true)
    private int product_purchased;
    
    @XmlElement(name = "quantity", required=true)
    private int quantity;
    
    @XmlElement(name = "buyer_id", required=true)
    private int buyer_id;
    
    @XmlElement(name = "consignee", required=true)
    private String consignee;
    
    @XmlElement(name = "deliv_address", required=true)
    private String deliv_address;
    
    @XmlElement(name = "postalcode", required=true)
    private String postalcode;
    
    @XmlElement(name = "phone", required=true)
    private String phone;
    
    @XmlElement(name = "creditcard", required=true)
    private String creditcard;
    
    @XmlElement(name = "verification", required=true)
    private String verification;
    
    @XmlElement(name = "purchase_date", required=true)
    private Date purchase_date;
    
    @XmlElement(name = "product_name", required=true)
    private String product_name;    
    
    @XmlElement(name = "price", required=true)
    private long price;
    
    @XmlElement(name = "seller_id", required=true)
    private int seller_id;
    
    @XmlElement(name = "image", required=true)
    private String image;
    
    @XmlElement(name = "buyer_username", required=true)
    private String buyer_username;
    
    @XmlElement(name = "seller_username", required=true)
    private String seller_username;
    
    @XmlElement(name = "status", required=true)
    private String status;
    
    public Purchase() {
        status = "invalid";
    }
    
    public Purchase(int id, int prod, int quan, int buyid, String cons, String addr, String pos, String phn, String cc, String verif, Timestamp date, String pname, long prc, String img, int sellid, String buyname, String sellname) {
        purchase_id = id;
        product_purchased = prod;
        quantity = quan;
        buyer_id = buyid;
        consignee = cons;
        deliv_address = addr;
        postalcode = pos;
        phone = phn;
        creditcard = cc;
        verification = verif;
        purchase_date = date;
        product_name = pname;
        price = prc;
        image = img;
        seller_id = sellid;
        buyer_username = buyname;
        seller_username = sellname;
        status = "valid";
    }
}
