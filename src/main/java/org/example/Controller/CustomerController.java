package org.example.Controller;

import org.example.Enums.LoanType;
import org.example.Interfaces.CustomerInterface;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerController implements CustomerInterface {

    public static void main(String[] args) {

    }
    //jdbc connectivity===================

    static Connection con = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;
    static Statement st = null;
    static int globalId = 0;
    //=======================================

    public static void viewBalance(int id) {
        con = DBConnection.createDBConnection();
        globalId = id;
        String query = "select customerId,customerBalance from customerAccount where customerId = "+id;
        double amt = 0.00;
        try {
            Statement st = con.createStatement();

            rs = st.executeQuery(query);

            while(rs.next())
            {
                System.out.format("%d\t%f\t\n", rs.getInt(1), rs.getDouble(2));
//                amt = rs.getDouble(2);
                System.out.println("-------------------------------------------");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }


    public static void addAmount(double amount,int id) {

        System.out.println("IDDDDDD = "+id);

        con = DBConnection.createDBConnection();

        String query = "select customerBalance from customerAccount where customerId = ?";

        try {

            Statement st = con.createStatement();
            rs = st.executeQuery("select customerBalance from customerAccount where customerId = "+id);

//            ps = con.prepareStatement(query);
//            ps.setInt(1,id);
            //rs = ps.executeQuery();

            rs.next();

            double currentAmount = rs.getDouble(1);

            //currentAmount = currentAmount + amount;


            try
            {
                String query2 = "update customerAccount set customerBalance = ? where customerId = ?";

                //Statement st2 = con.createStatement();
                //rs = st.executeQuery("update customerAccount set customerBalance = "+currentAmount+"where customerId ="+id);

                ps = con.prepareStatement(query2);

                ps.setDouble(1,amount + currentAmount);

                ps.setInt(2,id);

                ps.executeUpdate();

                System.out.println("balance successfully updated");

//                if(count!=0)
//                {
//                    System.out.println("Unable to update balance");
//                }
//                else
//                {
//                    System.out.println("balance successfully updated");
//                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void withdrawal(double amount,int id) {

        System.out.println("IDDDDDD = "+id);

        con = DBConnection.createDBConnection();

        String query = "select customerBalance from customerAccount where customerId = ?";

        try {

            Statement st = con.createStatement();
            rs = st.executeQuery("select customerBalance from customerAccount where customerId = "+id);

//            ps = con.prepareStatement(query);
//            ps.setInt(1,id);
            //rs = ps.executeQuery();

            rs.next();

            double currentAmount = rs.getDouble(1);

            if(amount > currentAmount)
            {
                System.out.println("Insufficient Balance");
            }
            else
            {
                try
                {
                    String query2 = "update customerAccount set customerBalance = ? where customerId = ?";

                    //Statement st2 = con.createStatement();
                    //rs = st.executeQuery("update customerAccount set customerBalance = "+currentAmount+"where customerId ="+id);

                    ps = con.prepareStatement(query2);

                    ps.setDouble(1,currentAmount - amount);

                    ps.setInt(2,id);

                    ps.executeUpdate();

                    System.out.println("Amount successfully withdrawn");

//                if(count!=0)
//                {
//                    System.out.println("Unable to update balance");
//                }
//                else
//                {
//                    System.out.println("balance successfully updated");
//                }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            //currentAmount = currentAmount + amount;



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


//    public static void withdrawal(double amount,int id) {
//
//        con = DBConnection.createDBConnection();
//
//        String query = "select customerBalance from customerAccount where id = ?";
//
//        try {
//            Statement st = con.createStatement();
//            rs = st.executeQuery("select customerBalance from customerAccount where customerId = "+id);
//
////            ps = con.prepareStatement(query);
////            ps.setInt(1,id);
//            //rs = ps.executeQuery();
//
//            rs.next();
//
//            double currentAmount = rs.getDouble(1);
//
//            if(amount > currentAmount)
//            {
//                System.out.println("Insufficient Balance");
//            }
//            else
//            {
//                try
//                {
//                    String query2 = "update customerAccount set customerBalance = ? where customerId = ?";
//
//                    //Statement st2 = con.createStatement();
//                    //rs = st.executeQuery("update customerAccount set customerBalance = "+currentAmount+"where customerId ="+id);
//
//                    ps = con.prepareStatement(query2);
//
//                    ps.setDouble(1,currentAmount - amount);
//
//                    ps.setInt(2,id);
//
//                    ps.executeUpdate();
//
//                    System.out.println("Amount successfully withdrawed");
//
//
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//
//    }


    public static double calcualteAmount(int principleAmount, int noOfYears,double rateOfInterst){
        return principleAmount+((principleAmount*noOfYears*rateOfInterst)/100);
    }

    public static void takeLoan(int principleAmount, int noOfYears, String loanType,int id) {

        con = DBConnection.createDBConnection();



        LoanType lType = null;
        if(loanType.equals(LoanType.HOME_LOAN))lType = LoanType.HOME_LOAN;
        else if(loanType.equals(LoanType.EDUCATION_LOAN))lType = LoanType.EDUCATION_LOAN;
        else if(loanType.equals(LoanType.CAR_LOAN))lType = LoanType.CAR_LOAN;
        else lType = LoanType.PERSONAL_LOAN;
        //home-10%  // car - 8.8%   //education 7.25    //personal - 11.2

        double finalAmount = 0.0;
        double emi = 0.0;
        if (lType.equals(LoanType.CAR_LOAN)){
            finalAmount = calcualteAmount(principleAmount,noOfYears,8.8);
        } else if (lType.equals(LoanType.HOME_LOAN)) {
            finalAmount = calcualteAmount(principleAmount,noOfYears,10.0);
        }
        else if (lType.equals(LoanType.EDUCATION_LOAN)) {
            finalAmount = calcualteAmount(principleAmount,noOfYears,7.25);
        }else  {
            finalAmount = calcualteAmount(principleAmount,noOfYears,11.2);
        }
        emi = finalAmount/(noOfYears*12);

        try{
            con = DBConnection.createDBConnection();
            //1-id  //2  - balance  //3 - loan  //4 - emi
            String query = "update customerAccount set loan = ? , emi = ?";


            ps = con.prepareStatement(query);
            ps.setDouble(1,finalAmount);
            ps.setDouble(2,emi);


            int count  = ps.executeUpdate();
            if(count!=0)System.out.println("Loan sanctioned successfully!!😊");


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}