package org.example.Interfaces;

import org.example.Enums.LoanType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface CustomerInterface {
    public void viewBalance();
    public void addAmount(int amount);
    public void withdrawal(int amount);
    public void takeLoan(int principleAmount, int noOfMonths, LoanType loanType);

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
