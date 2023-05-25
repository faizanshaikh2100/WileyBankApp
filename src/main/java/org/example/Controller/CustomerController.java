package org.example.Controller;

import com.mysql.cj.jdbc.util.ResultSetUtil;
import org.example.Enums.LoanType;
import org.example.Interfaces.CustomerInterface;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerController implements CustomerInterface {

    //jdbc connectivity=================
    DBConnection dbConnection = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Statement st = null;

    //customer
    @Override
    public void viewBalance() {

    }

    @Override
    public void addAmount(int amount) {

    }

    @Override
    public void withdrawal(int amount) {

    }
    public double calcualteAmount(int principleAmount, int noOfYears,double rateOfInterst){
        return (principleAmount*noOfYears*rateOfInterst)/100;
    }

    public void takeLoan(int principleAmount, int noOfYears, String loanType) {

        LoanType lType = null;
       for(LoanType loanType1:LoanType.values()){

           if(loanType.equals(loanType1)){
               lType =  loanType1;
           }
       }
       //home-10%
        // car - 8.8%
        //education 7.25
        //personal - 11.2

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

        }catch (Exception e){

        }

    }
}
