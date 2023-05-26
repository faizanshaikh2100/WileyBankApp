package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBConnection {
    static Connection conn;

    public static Connection createDBConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            // connection string
            String url = "jdbc:mysql://localhost:3306/wileybankapp";
            String username = "root";
            String password = "yedtutti";

            conn = DriverManager.getConnection(url,username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}