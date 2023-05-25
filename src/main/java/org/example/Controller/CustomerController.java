package org.example.Controller;

import com.mysql.cj.jdbc.util.ResultSetUtil;
import org.example.Enums.LoanType;
import org.example.Interfaces.CustomerInterface;
import org.example.util.DBConnection;

import java.sql.*;

public class CustomerController implements CustomerInterface {

    //jdbc connectivity=================
    DBConnection dbConnection = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Statement st = null;

    //customer
    @Override
    public void viewBalance(int id) {

        String query = "select customerId,customerBalance from customerAccount where id = ?";

       try {
          ps = con.prepareStatement(query);

          rs = ps.executeQuery();

          while(rs.next())
          {
              System.out.format("%d\t%f\t\n", rs.getInt(1), rs.getDouble(2));
              System.out.println("-------------------------------------------");
          }
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }



    }

    @Override
    public void addAmount(double amount,int id) {

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

    @Override
    public void withdrawal(double amount,int id) {

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
