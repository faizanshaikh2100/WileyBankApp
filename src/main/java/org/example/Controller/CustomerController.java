package org.example.Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.Enums.LoanType;
import org.example.Interfaces.CustomerInterface;
import org.example.Model.Admin;
import org.example.util.DBConnection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;

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
    static int customerId;
        public static void setCustomerId(int id){
            customerId = id;
        }
    public static void withdrawal(double amount,int id) {

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
    public static void downloadPdf(int id) {
        // Initialize variables
        String name = null;
        String email = null;
        String address = null;
        double balance = 0;
        String loanType = null;
        double amountToBePaid = 0;
        double emi = 0;

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM customers WHERE id = " + id);

            while (rs.next()) {
                name = rs.getString(4);
                email = rs.getString(3);
                address = rs.getString(5);
            }

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM customerAccount WHERE customerId = " + id);

            if (rs.next()) {
                balance = rs.getDouble(2);
                loanType = rs.getString(3);
                amountToBePaid = rs.getDouble(4);
                emi = rs.getDouble(5);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String path = "C:\\Users\\Faizan\\Downloads\\" + name + ".pdf";

        Document document = new Document();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            PdfWriter.getInstance(document, fileOutputStream);

            document.open();

            // Create a Font object with font size 14
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 14);

            document.add(new Paragraph("==================================", font));
            document.add(new Paragraph("Account Holder Name : " + name, font));
            document.add(new Paragraph("Address             : " + address, font));
            document.add(new Paragraph("Email               : " + email, font));
            document.add(new Paragraph("Account Balance     : " + balance, font));
            document.add(new Paragraph("                                   ", font));
            document.add(new Paragraph("============Loan Details===============", font));
            document.add(new Paragraph("LoanType            : " + loanType, font));
            document.add(new Paragraph("Amount to be Paid   : " + amountToBePaid, font));
            document.add(new Paragraph("Emi                 : " + emi, font));

            document.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
            finalAmount = calcualteAmount(principleAmount,noOfYears, Admin.getCarLoanROI());
        } else if (lType.equals(LoanType.HOME_LOAN)) {
            finalAmount = calcualteAmount(principleAmount,noOfYears,Admin.getHomeLoanROI());
        }
        else if (lType.equals(LoanType.EDUCATION_LOAN)) {
            finalAmount = calcualteAmount(principleAmount,noOfYears,Admin.getEducationLoanROI());
        }else  {
            finalAmount = calcualteAmount(principleAmount,noOfYears,Admin.getPersonalLoanROI());
        }
        emi = finalAmount/(noOfYears*12);

        try{
            con = DBConnection.createDBConnection();
            //1-id  //2  - balance  //3 - loan  //4 - emi
            String query = "update customerAccount set loanType = ? , amountToBePaid = ?,emi = ?";


            ps = con.prepareStatement(query);
            ps.setString(1,loanType);
            ps.setDouble(2,finalAmount);
            ps.setDouble(3,emi);

            int count  = ps.executeUpdate();
            if(count!=0)System.out.println("Loan sanctioned successfully!!😊");


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}