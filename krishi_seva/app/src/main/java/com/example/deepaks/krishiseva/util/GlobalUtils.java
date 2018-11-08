package com.example.deepaks.krishiseva.util;

import android.telephony.PhoneNumberUtils;

public class GlobalUtils {

    /**
     * @param emailAddress the target char sequence
     * @return returns the valid email boolean value
     * @description method for checking the valid email
     * @author deeapks
     */
    public static boolean isValidEmail(CharSequence emailAddress) {
        return emailAddress == null || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }


    /**
     * @param phoneNumber the phone number to validate
     * @return the valid phone number flag
     * @author deepaks
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (!PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)
                || phoneNumber.length() < 9
                ) {
            return false;
        }
        return true;

    }
}
