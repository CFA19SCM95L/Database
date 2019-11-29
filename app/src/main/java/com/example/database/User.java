package com.example.database;

import java.io.Serializable;

public class User implements Serializable {

    private String userID;
    private String accountNumber;
    private String cardNumber;
    private String password;

    public User(String userID, String accountNumber, String cardNumber, String password) {
        this.userID = userID;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
