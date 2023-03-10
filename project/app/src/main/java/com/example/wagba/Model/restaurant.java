package com.example.wagba.Model;

import java.io.Serializable;

public class restaurant extends card_restaurent_meal implements Serializable {
    private String id ;
    private String delivery_fee;
    private String img_bg;


    public restaurant() {}

    public restaurant(String name, String details, String img, String delivery_fee , String img_bg) {
        super(name, details,img);
        this.delivery_fee = delivery_fee;
        this.img_bg = img_bg;
    }

    public restaurant(String id,String name, String details, String img, String delivery_fee , String img_bg) {
        super(name, details,img);
        this.id= id;
        this.delivery_fee = delivery_fee;
        this.img_bg = img_bg;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public String getImg_bg() {
        return img_bg;
    }

    public void setImg_bg(String img_bg) {
        this.img_bg = img_bg;
    }

    @Override
    public String toString() {
        return "restaurant{" +
                "name='" + super.getName() + '\'' +
                ", details='" + super.getDetails() + '\'' +
                ", img='" + super.getImg() + '\'' +
                ", delivery_fee='" + delivery_fee + '\'' +
                ", logo='" + img_bg + '\'' +
                '}';
    }
}
