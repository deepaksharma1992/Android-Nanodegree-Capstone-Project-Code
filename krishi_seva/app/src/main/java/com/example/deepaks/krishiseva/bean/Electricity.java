package com.example.deepaks.krishiseva.bean;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Electricity {
    public int id;
    public String cutOffDate, cutOffTime, fromTime, toTime, cutOfRegion;

    public Electricity() {

    }

    public int getId() {
        return id;
    }

    public String getCutOffDate() {
        return cutOffDate;
    }

    public String getCutOffTime() {
        return cutOffTime;
    }

    public String getFromTime() {
        return fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public String getCutOfRegion() {
        return cutOfRegion;
    }
}

