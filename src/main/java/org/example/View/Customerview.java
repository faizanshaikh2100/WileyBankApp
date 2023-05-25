package org.example.View;

import org.example.Controller.CustomerController;

import java.util.Scanner;

public class Customerview {
   static int id;
    public Customerview(int id){
        this.id = id;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       while (true){
           System.out.println("Enter to continue....");
           sc.nextLine();
           System.out.println("----------------Customer Portal----------------");
           System.out.println();

           System.out.println("Choose an option");
           System.out.println("------------------");
           System.out.println("1. View Account Balance");
           System.out.println("2. Add Amount");
           System.out.println("3. Withdraw Amount");
           System.out.println("4. Take Loan");

           int option = sc.nextInt();
           switch (option){
               case 1:

                   CustomerController.viewBalance(id);
                   break;
               case 2:
                   System.out.println("How much amount you want to add?");
                   int amount  = sc.nextInt();
                   CustomerController.addAmount(amount,id);
                   break;
               case 3:
                   System.out.println("How much amount you want to Withdraw?");
                   int amount2  = sc.nextInt();
                   CustomerController.withdrawal(amount2,id);
                   break;
               case 4:
                   System.out.println("Enter the amount of loan u want to take");
                   int princpleAmount = sc.nextInt();

                   System.out.println("For how many years?");
                   int years = sc.nextInt();

                   System.out.println("Choose loan type");
                   System.out.println("      1. Home loan");
                   System.out.println("      2. Personal loan");
                   System.out.println("      3. Car loan");
                   System.out.println("      4. Education loan");
                   int opt = sc.nextInt();
                   String loanType   = "";
                   switch (opt){
                       case 1:loanType = "HOME_LOAN";
                           break;
                       case 2:loanType = "PERSONAL_LOAN";
                           break;
                       case 3:loanType = "CAR_LOAN";
                           break;
                       case 4:loanType = "EDUCATION_LOAN";
                       break;
                       default:
                           System.out.println("Wrong choice");
                           break;
                   }

                   CustomerController.takeLoan(princpleAmount,years,loanType,id);
                   break;
               default:
                   break;
           }





       }
    }
}
