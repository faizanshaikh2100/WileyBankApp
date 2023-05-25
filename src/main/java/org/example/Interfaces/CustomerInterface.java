package org.example.Interfaces;

import org.example.Enums.LoanType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface CustomerInterface {
    public void viewBalance(int id) throws SQLException;
    public void addAmount(double amount,int id);
    public void withdrawal(double amount,int id);
    public void takeLoan(int principleAmount, int noOfMonths, String loanType);


//    public void getEmployeeById(int id) throws SQLException, ClassNotFoundException {
//        con = DBConnection.createDBConnection();
//
//        String query = "SELECT * FROM employee where id = " + id;
//
//        try {
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery(query);
//
//            while (rs.next()) {
//                System.out.format("%d\t%s\t%f\t%d\\t", rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
