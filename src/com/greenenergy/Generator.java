package com.greenenergy;

import java.io.Serializable;
import java.util.Comparator;

public abstract class Generator implements Serializable {
    private int Price;
    private String Make;
    private String Model;

    //private static final long serialVersionUID = 1234L;

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }
}
