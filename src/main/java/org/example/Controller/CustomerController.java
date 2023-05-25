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

    public static double viewBalance(int id) {
    globalId = id;
        String query = "select customerId,customerBalance from customerAccount where id = ?";
            double amt = 0.00;
        try {
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            while(rs.next())
            {
                System.out.format("%d\t%f\t\n", rs.getInt(1), rs.getDouble(2));
                amt = rs.getDouble(2);
                System.out.println("-------------------------------------------");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


 return amt;
    }


    public static void addAmount(double amount,int id) {

        String query = "select customerBalance from customerAccount where id = ?";

        try {
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            double currentAmount = rs.getDouble(2);

            try
            {
                String query2 = "update customerAccount set customerBalance = ? where customerId = ?";
                ps = con.prepareStatement(query2);
                ps.setDouble(2,amount + currentAmount);

                int count = ps.executeUpdate();

                if(count!=0)
                {
                    System.out.println("Unable to update balance");
                }
                else
                {
                    System.out.println("balance successfully updated");
                }
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

        String query = "select customerBalance from customerAccount where id = ?";

        try {
            ps = con.prepareStatement(query);

            rs = ps.executeQuery();

            double currentAmount = rs.getDouble(2);

            if(amount > currentAmount)
            {
                System.out.println("Insufficient Balance");
            }
            else
            {
                try
                {
                    String query2 = "update customerAccount set customerBalance = ? where customerId = ?";
                    ps = con.prepareStatement(query2);
                    ps.setDouble(2,currentAmount - amount);

                    int count = ps.executeUpdate();

                    if(count!=0)
                    {
                        System.out.println("Unable to Withdraw");
                    }
                    else
                    {
                        System.out.println("Amount Withdrawed Successfully");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }


    public static double calcualteAmount(int principleAmount, int noOfYears,double rateOfInterst){
        return principleAmount+((principleAmount*noOfYears*rateOfInterst)/100);
    }

    public static void takeLoan(int principleAmount, int noOfYears, String loanType,int id) {

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
          String query = "insert into customerAccount values(?,?,?,?)";


          ps = con.prepareStatement(query);
          ps.setInt(1,id);
          ps.setDouble(2,viewBalance(globalId));
          ps.setDouble(3,finalAmount);
          ps.setDouble(4,emi);

          int count  = ps.executeUpdate();
          if(count!=0)System.out.println("Loan sanctioned successfully!!😊");


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}
