package com.example.deepaks.krishiseva.bean;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Vendor {
    public int id;
    public String bestPrice, items, phoneNumber, vendorName;

    public Vendor() {

    }

    public int getId() {
        return id;
    }

    public String getBestPrice() {
        return bestPrice;
    }

    public String getItems() {
        return items;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getVendorName() {
        return vendorName;
    }
}
