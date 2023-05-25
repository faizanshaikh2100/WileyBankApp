package org.example.functionalities;

import java.sql.*;
import java.util.Scanner;

public class SignUp {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/wileybankapp";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Prepare the insert statement
            String insertQuery = "INSERT INTO customers (id, password, email, full_name, address) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertQuery);

            // Get user input for customer details
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter customer ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter full name: ");
            String fullName = scanner.nextLine();

            System.out.print("Enter address: ");
            String address = scanner.nextLine();


            stmt.setString(1, id);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, fullName);
            stmt.setString(5, address);

            stmt.executeUpdate();
            System.out.println("Customer inserted successfully!");

            stmt.close();
            conn.close();
            scanner.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }
}
/*
CREATE TABLE customers (
    id varchar(10) PRIMARY KEY ,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    address VARCHAR(200)
);

 */