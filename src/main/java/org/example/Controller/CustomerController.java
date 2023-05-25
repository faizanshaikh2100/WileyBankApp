package org.example.Controller;

import org.example.Enums.LoanType;
import org.example.Interfaces.CustomerInterface;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class CustomerController implements CustomerInterface {

    public static void main(String[] args) {
         takeLoan(69043,2,"HOME_LOAN",2);
    }
    //jdbc connectivity===================

    static Connection con = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;
    static Statement st = null;
   //=======================================

    public static double viewBalance() {

        return 0;
    }

    public static void addAmount(int amount) {

    }

    public static void withdrawal(int amount) {

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
          ps.setDouble(2,viewBalance());
          ps.setDouble(3,finalAmount);
          ps.setDouble(4,emi);

          int count  = ps.executeUpdate();
          if(count!=0)System.out.println("Loan sanctioned successfully!!😊");


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
