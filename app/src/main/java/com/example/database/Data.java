package com.example.database;

import java.io.Serializable;

public class Data implements Serializable {


    private String productName;
    private int quantity;
    private double salesAmount;

    public Data(String productName, int quantity, double salesAmount) {
        this.productName = productName;
        this.quantity = quantity;
        this.salesAmount = salesAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }
}
