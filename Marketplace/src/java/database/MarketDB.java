/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 *
 * @author Asus
 */
public class MarketDB {
    private Connection conn = null;
 
    public MarketDB() {
        final String url="jdbc:mysql://localhost/saleprojectmarket";
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
        Statement sta = conn.createStatement();
        return sta.executeQuery(sql);
    }

    public int update(String sql) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.executeUpdate(sql);
    }

    @Override
    protected void finalize() throws Throwable {
        if (conn != null && !conn.isClosed()) {
                conn.close();
        }
    }
}
