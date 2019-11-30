package com.example.database;

import java.io.Serializable;

public class Order implements Serializable {

    private String orderNumber;
    private String productName;
    private int quantity;
    private double totalPrice;
    public String realOrderNumber;

    public Order(String orderNumber, String productName, int quantity, double totalPrice) {
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
