package org.example.Model;

import org.example.Enums.AccountType;

public class Customer {
    private int id;//customerAccount foreign key
    private String name;
    private String email;
    private String mobile;
    private String address;
    private String annualIncome;
    private String password;
    private AccountType accountType;

    public int getId() {
        return id;
    }

    public Customer() {
    }

    public Customer(int id, String name, String email, String mobile, String address, String annualIncome, String password, AccountType accountType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.annualIncome = annualIncome;
        this.password = password;
        this.accountType = accountType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
