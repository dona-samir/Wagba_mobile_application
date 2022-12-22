package com.example.wagba.database;

public class card_restaurent_meal {
    private String name;
    private String details;
    private String  img;


    public card_restaurent_meal(){

    }

    public card_restaurent_meal(String name, String details, String img) {
        this.name = name;
        this.details = details;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    @Override
    public String toString() {
        return "card_restaurent_meal{" +
                "name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
