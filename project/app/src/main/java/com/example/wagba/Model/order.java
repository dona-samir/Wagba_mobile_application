package com.example.wagba.Model;

public class order {

    //private restaurant restaurant;
    private String res_title;
    private int res_img;
    private String order_id;
    private String order_date;
    private String order_status;

//    public  int getRestaurantimg() {
//        return restaurant.getImg();
//    }
//
//
//
//    public  String getRestauranttitle() {
//        return restaurant.getName();
//    }

//    public void setRestaurant(com.example.wagba.Model.restaurant restaurant) {
//        this.restaurant = restaurant;
//    }


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

//    public order(com.example.wagba.Model.restaurant restaurant, String order_id, String order_date, String order_status) {
//        this.restaurant = restaurant;
//        this.order_id = order_id;
//        this.order_date = order_date;
//        this.order_status = order_status;
//    }


    public order(String res_title, int res_img, String order_id, String order_date, String order_status) {
        this.res_title = res_title;
        this.res_img = res_img;
        this.order_id = order_id;
        this.order_date = order_date;
        this.order_status = order_status;
    }

    public String getRes_title() {
        return res_title;
    }

    public void setRes_title(String res_title) {
        this.res_title = res_title;
    }

    public int getRes_img() {
        return res_img;
    }

    public void setRes_img(int res_img) {
        this.res_img = res_img;
    }
}
