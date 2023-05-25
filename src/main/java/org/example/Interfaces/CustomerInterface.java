package org.example.Interfaces;

import java.sql.SQLException;

public interface CustomerInterface {

    public static double viewBalance(){

        return 0;
    }
    public static void addAmount(int amount){}
    public static void withdrawal(int amount){}

    public static void takeLoan(int principleAmount, int noOfMonths, String loanType, int id) {

    }
    public void viewBalance(int id) ;
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
