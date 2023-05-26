package org.example.functionalities;

import org.example.View.Customerview;

import java.sql.*;
import java.util.Scanner;

public class Login {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/wileybankapp";
    static final String USER = "root";
    static final String PASS = "yedtutti";

    public static void main(String[] args) {
        System.out.println();
        System.out.println();
        System.out.println("====== LOGIN ======");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Get user input for login credentials
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter customer ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();


            String loginQuery = "SELECT * FROM customers WHERE id = ? AND password = ?";
            stmt = conn.prepareStatement(loginQuery);

            stmt.setString(1, id);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                String[] arg = new String[1];
                arg[0] = id;
                Customerview.main(arg);
                // Perform further actions or display user information
            } else {
                System.out.println("Invalid login credentials!");
                System.out.println("Try Signing Up..");
                SignUp.main(args);
                // Handle unsuccessful login
            }



        } catch (SQLException se) {

        } catch (Exception e) {

        }
    }
}
