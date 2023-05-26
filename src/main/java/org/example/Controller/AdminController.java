package org.example.Controller;

import org.example.util.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class AdminController {
    public static void main(String[] args) {

    }

    public static void viewAllCustomers() {
        try {
            Connection con = DBConnection.createDBConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customers");

            System.out.printf("%-10s %-10s %-10s%n", "Id", "Name", "Email");
            System.out.println();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(4);
                String email = rs.getString(3);
                System.out.printf("%-10s %-10s %-10s%n", id+"", name+"", email+"");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteCustomer(int id){

        Connection con = DBConnection.createDBConnection();

        String query = "delete from customers where id = ?";
        String query2 = "delete from customerAccount where customerId = ?";

        try {

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,id);
            int count = ps.executeUpdate();
            ps = con.prepareStatement(query2);
            ps.setInt(1,id);
            ps.executeUpdate();
            if (count!=0) System.out.println("Successfully deleted Customer and related account!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
