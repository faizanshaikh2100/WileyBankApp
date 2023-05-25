package org.example.Model;

public class Admin {
        private int id;
        private String password;

    public int getId() {
        return id;
    }

    public Admin() {
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

    public Admin(int id, String password) {
        this.id = id;
        this.password = password;
    }
}
