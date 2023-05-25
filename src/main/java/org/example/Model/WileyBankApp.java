package org.example.Model;

import org.example.Controller.CustomerController;
import org.example.View.Customerview;
import org.example.functionalities.Login;
import org.example.functionalities.SignUp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WileyBankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
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
                        System.out.println("    1. Login ");
                        System.out.println("    2. Register ");
                        int opt2 = sc.nextInt();
                        if(opt2==1) Login.main(args);
                        else if(opt2==2) SignUp.main(args);
                        else System.out.println("Wrong choice");
                        break;
                    default:
                        System.out.println("Wrong choice");
                        break;
                }

            }
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }

    }
}