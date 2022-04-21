package com.example.eshop;

public class Helper {

    String name,mail,Pass,mobile;

    public Helper() {

    }

    public Helper(String name, String email, String password, String mobile) {
        this.name = name;
        this.mail = email;
        this.Pass = password;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public String getPassword() {
        return Pass;
    }

    public void setPassword(String password) {
        this.Pass = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

