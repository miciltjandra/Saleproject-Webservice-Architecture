/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
 
public class IdentityDB {
 
	private Connection conn = null;
        private Statement sta = null;
 
	public IdentityDB() {
            final String url="jdbc:mysql://localhost/saleprojectaccount";
            final String user_name = "wbd";
            final String password = "twinbaldchicken";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, user_name, password);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}
 
	public ResultSet select(String sql) throws SQLException {
            sta = conn.createStatement();
            return sta.executeQuery(sql);
	}
        
        public int update(String sql) throws SQLException {
            sta = conn.createStatement();
            return sta.executeUpdate(sql);
        }
        
        public void closeDB() throws SQLException {
            if (conn != null && !conn.isClosed()) {
                    conn.close();
            }
            if (sta != null && !sta.isClosed()) {
                    sta.close();
            }
        }
 
	@Override
	protected void finalize() throws Throwable {
            if (conn != null && !conn.isClosed()) {
                    conn.close();
            }
	}
 
}
