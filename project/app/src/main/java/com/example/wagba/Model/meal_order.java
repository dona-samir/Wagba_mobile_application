package com.example.wagba.Model;

public class meal_order extends meal{
    String quantity;

    public meal_order(String name, String details, String img, String price, String id, String restaurant_id, String quantity) {
        super(name, details, img, price, id, restaurant_id);
        this.quantity = quantity;
    }

    public meal_order() {
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
