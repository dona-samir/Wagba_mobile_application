package com.example.wagba.Model;

public class meal extends card_restaurent_meal {

    private String price;
    private String id;
    private String restaurant_id;
    private int available;

    public meal (){};
    public meal(String name, String details, String img) {
        super(name, details,img);
    }

    public meal(String name, String details, String img, String price,String id,String restaurant_id) {
        super(name, details,img);
        this.id = id;
        this.restaurant_id = restaurant_id;
        this.price = price;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "meal{" +
                "price='" + price + '\'' +
                ", id='" + id + '\'' +
                ", restaurant_id='" + restaurant_id + '\'' +
                ", available=" + available +
                '}';
    }
}
