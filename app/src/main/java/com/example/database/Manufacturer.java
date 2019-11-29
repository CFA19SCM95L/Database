package com.example.database;

import java.io.Serializable;

public class Manufacturer implements Serializable {

    private String manufacturerID;
    private String companyName;
    private String product;

    public Manufacturer(String manufacturerID, String companyName, String product) {
        this.manufacturerID = manufacturerID;
        this.companyName = companyName;
        this.product = product;
    }

    public String getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(String manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
