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
    public static void downloadPdf(){}


}
