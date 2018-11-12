package com.example.deepaks.krishiseva.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationUtils {

    public static String getAddress(Context context, double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());
        String address = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//            address = addresses.get(0).getAddressLine(0);


            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
    /*        String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();*/

            address = city + GlobalConstant.COMMA + GlobalConstant.SPACE_1 + state;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }
}
