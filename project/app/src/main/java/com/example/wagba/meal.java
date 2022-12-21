package com.example.wagba;

public class meal {

    private String name;
    private String details;
    private int  img;
    private double price;

    public meal(String name, String details, int img) {

        this.name = name;
        this.details = details;
        this.img = img;
    }

    public meal(String name, String details, int img, double price) {
        this.name = name;
        this.details = details;
        this.img = img;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
