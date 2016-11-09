/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import database.IdentityDB;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class Token {
    public Token() {
        
    }
    
    public static String generateToken(String user_id) {
        SecureRandom random = new SecureRandom();
        String token = new BigInteger(50, random).toString(32);
        
        IdentityDB db = new IdentityDB();
        String sql = "INSERT INTO accesstoken VALUES('"+token+"', now(),'"+user_id+"')";
        try{
            db.update(sql);
        }
        catch(SQLException e) {
            
        }   
        
        return token;
    }
    
    public static String validateToken(String token, int user_id) {
        return "valid";
    }
    
    public static void extendToken(String token) {
        
    }
    
}
