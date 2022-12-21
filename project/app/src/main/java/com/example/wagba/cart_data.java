package com.example.wagba;

import java.util.ArrayList;

public class cart_data {
    ArrayList<meal> List = new ArrayList<>();
    private double cart_total;

    public cart_data(ArrayList<meal> list, double cart_total) {
        List = list;
        this.cart_total = cart_total;
    }
}
