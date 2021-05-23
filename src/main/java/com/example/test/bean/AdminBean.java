package com.example.test.bean;

public class AdminBean {
    private int adminid;
    private String adminname;
    private String adminpassword;
    private String adminbalance;

    public String getAdminbalance() {
        return adminbalance;
    }

    public void setAdminbalance(String adminbalance) {
        this.adminbalance = adminbalance;
    }

    public int getId() {
        return adminid;
    }

    public void setId(int id) {
        this.adminid = id;
    }

    public String getName() {
        return adminname;
    }

    public void setName(String name) {
        this.adminname = name;
    }

    public String getPassword() {
        return adminpassword;
    }

    public void setPassword(String password) {
        this.adminpassword = password;
    }
}
