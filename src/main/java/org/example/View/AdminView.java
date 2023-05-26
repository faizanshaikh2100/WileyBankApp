package org.example.View;

import org.example.Controller.AdminController;
import org.example.Model.Admin;

import java.util.Scanner;

public class AdminView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Username");
        String username =sc.nextLine();
        System.out.println("Enter Password");
        String password = sc.nextLine();

        if(username.equals("admin")&&password.equals("root")){

            while (true) {
                System.out.println("Enter to continue....");
                sc.nextLine();
                System.out.println();
                System.out.println("=================Admin Portal=================");
                System.out.println();
                System.out.println("Choose an option");
                System.out.println("1. View all  customers");
                System.out.println("2. Delete a customer");
                System.out.println("3. Modify Rate of Interest");
                int option = sc.nextInt();
                switch (option){
                    case 1:
                        AdminController.viewAllCustomers();
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Enter the id of the customer to delete:");
                        int id = sc.nextInt();
                        AdminController.deleteCustomer(id);
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Choose which ROI u want to modify");
                        System.out.println("    1. Home loan ROI");
                        System.out.println("    2. Car loan ROI");
                        System.out.println("    3. Personal loan ROI");
                        System.out.println("    4. Education loan ROI");
                        int opt = sc.nextInt();
                        switch (opt){
                            case 1:
                                System.out.println("Enter new Home loan ROI amount: ");
                                int ans = sc.nextInt();
                                Admin.setHomeLoanROI(ans);
                                break;
                            case 2:
                                System.out.println("Enter new Car loan ROI amount: ");

                                int ans2 = sc.nextInt();
                                Admin.setCarLoanROI(ans2);
                                break;
                            case 3:
                                System.out.println("Enter new Personal loan ROI amount: ");
                                int ans3 = sc.nextInt();
                                Admin.setPersonalLoanROI(ans3);
                                break;
                            case 4:
                                System.out.println("Enter new Education loan ROI amount: ");
                                int ans4 = sc.nextInt();
                                Admin.setEducationLoanROI(ans4);
                                break;
                            default:
                                System.out.println("Wrong choice");
                                break;

                        }
                        break;
                    default:
                        break;
                }
            }
        }else {
            System.out.println("Incorrect Username or password!!");
        }
    }
}
