package com.example.deepaks.krishiseva.bean;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String userName,email,password,phoneNumber,location;

    public User(){

    }

    public User(String userName, String email, String password, String phoneNumber, String location) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }


    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }
}
