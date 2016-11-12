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
import java.util.logging.Level;
import java.util.logging.Logger;

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
            db.closeDB();
        }
        catch(SQLException e) {
            
        }   
        
        return token;
    }
    
    public static String validateToken(String token, int user_id) {
        return "valid";
    }
    
    public static void extendToken(String token) {
        IdentityDB db = new IdentityDB();
        String query = "UPDATE accesstoken SET time = NOW()\n" +
                        "WHERE token = \"" + token + "\"";
        try {
            db.update(query);
            db.closeDB();
        } catch (SQLException ex) {
            Logger.getLogger(Token.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
