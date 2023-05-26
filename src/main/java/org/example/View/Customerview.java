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
           System.out.println("5. Download PDF");

           int option = sc.nextInt();
           switch (option){
               case 1:
                   int idd4 = Integer.parseInt(args[0]);
                   CustomerController.viewBalance(idd4);
                   break;
               case 2:
                   System.out.println("How much amount you want to add?");
                   double amount  = sc.nextDouble();
                   int idd = Integer.parseInt(args[0]);
                   CustomerController.addAmount(amount,idd);
                   break;
               case 3:
                   System.out.println("How much amount you want to Withdraw?");

                   int amount2  = sc.nextInt();

                   int idd2 = Integer.parseInt(args[0]);
                   CustomerController.withdrawal(amount2,idd2);
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

                   int idd3 = Integer.parseInt(args[0]);
                   CustomerController.takeLoan(princpleAmount,years,loanType,idd3);
                   break;
               case 5:
                   int idd5 = Integer.parseInt(args[0]);
                   CustomerController.downloadPdf(idd5);
                   break;
               default:
                   break;
           }
       }
    }
}