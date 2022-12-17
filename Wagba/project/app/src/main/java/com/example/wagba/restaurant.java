package com.example.wagba;

public class restaurant {
    private String name;
    private String details;
    private int  img;
    private double delivery_fee;

    public restaurant(String name, String details, int img) {
        this.name = name;
        this.details = details;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getdetails() {
        return details;
    }

    public void setdetails(String age) {
        this.details = details;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

}
