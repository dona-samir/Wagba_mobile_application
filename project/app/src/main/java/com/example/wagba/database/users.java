package com.example.wagba.database;

public class users {
    private String name;
    private String email;
    private String phone_no;

    public users(String name, String email, String phone_no) {
        this.name = name;
        this.email = email;
        this.phone_no = phone_no;
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

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
