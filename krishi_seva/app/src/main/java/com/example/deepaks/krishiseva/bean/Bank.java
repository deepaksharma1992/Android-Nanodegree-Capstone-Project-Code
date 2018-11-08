package com.example.deepaks.krishiseva.bean;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Bank {
    public int id;
    public double interestRate;
    public float rating;
    public boolean isGovtBank;
    public String bankName, phoneNumber;


    public Bank() {

    }

    public int getId() {
        return id;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public float getRating() {
        return rating;
    }

    public boolean isGovtBank() {
        return isGovtBank;
    }

    public String getBankName() {
        return bankName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}



