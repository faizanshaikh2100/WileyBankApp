package org.example.Model;

import org.example.Controller.CustomerController;
import org.example.View.Customerview;
import org.example.functionalities.Login;

import java.util.Scanner;

public class WileyBankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

            while(true) {

                System.out.println();
                System.out.println("=============WILEY BANK APP🏦===============");
                System.out.println();

                System.out.println("Choose an option");

                System.out.println("1. Admin Portal");
                System.out.println("2 .Customer Portal");
                int option =  sc.nextInt();

                switch (option){
                    case 1:
                        break;
                    case 2:
                        Login.main(args);
                        break;
                    default:
                        System.out.println("Wrong choice");
                        break;
                }

            }


    }
}
