package com.example.deepaks.krishiseva.bean;

import com.example.deepaks.krishiseva.util.GlobalConstant;
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

    @Override
    public String toString() {
        return (id + 1) + GlobalConstant.SPACE_1
                + cutOfRegion
                + GlobalConstant.COMMA
                + GlobalConstant.SPACE_1
                + cutOffTime;
    }
}

