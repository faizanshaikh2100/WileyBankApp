package org.example.Model;

public class Admin {
        private int id;
        private String password;
        static double homeLoanROI;
         static double carLoanROI;
        static double educationLoanROI;
        static double personalLoanROI;

    public Admin() {
    }

    public Admin(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static double getHomeLoanROI() {
        return homeLoanROI;
    }

    public static void setHomeLoanROI(double homeLoanROI) {
        Admin.homeLoanROI = homeLoanROI;
    }

    public static double getCarLoanROI() {
        return carLoanROI;
    }

    public static void setCarLoanROI(double carLoanROI) {
        Admin.carLoanROI = carLoanROI;
    }

    public static double getEducationLoanROI() {
        return educationLoanROI;
    }

    public static void setEducationLoanROI(double educationLoanROI) {
        Admin.educationLoanROI = educationLoanROI;
    }

    public static double getPersonalLoanROI() {
        return personalLoanROI;
    }

    public static void setPersonalLoanROI(double personalLoanROI) {
        Admin.personalLoanROI = personalLoanROI;
    }
}
