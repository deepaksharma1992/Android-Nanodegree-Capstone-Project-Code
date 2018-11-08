package com.example.deepaks.krishiseva.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * created by deepaks on 18 oct 2018.
 * description class for saving the preference value
 */
public class PreferenceUtils {

    private static final SharedPreferences sharedPreferences = KrishiSevaApplication
            .getAppContext()
            .getSharedPreferences(GlobalConstant.GLOBAL_PREFERENCE_KEY
                    , Context.MODE_PRIVATE);

    /**
     * @param key the key for shared preference
     * @return the value of preference
     * @author deeapks
     * @description method returns the value for shared preference
     */
    public static String getString(String key) {
        return sharedPreferences.getString(key, GlobalConstant.BLANK);
    }

    /**
     * @param keyPreference   the key for shared preference
     * @param valuePreference the value for string preference
     * @author deeapks
     * @date 28 feb 2018
     * @description method sets the value for shared preference
     */
    public static void setString(String keyPreference, String valuePreference) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(keyPreference, valuePreference);
        prefsEditor.apply();
    }

    /**
     * @param keyPreference the key for shared preference
     * @param value         the value for boolean preference
     * @author deeapks
     * @description method sets the value for shared preference
     */
    public static void setBoolean(String keyPreference, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(keyPreference, value);
        prefsEditor.apply();
    }

    /**
     * @param key the key for shared preference
     * @return the value of preference
     * @author deeapks
     * @description method returns the value for shared preference
     */
    public static boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public static void clearAllSharedPreferences() {
        sharedPreferences.edit().clear().apply();
    }



}
