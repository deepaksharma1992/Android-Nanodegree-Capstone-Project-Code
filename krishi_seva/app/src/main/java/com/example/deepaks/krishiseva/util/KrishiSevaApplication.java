package com.example.deepaks.krishiseva.util;

import android.app.Application;
import android.content.Context;

/**
 * description class to get the application context
 */

public class KrishiSevaApplication extends Application {
    private static KrishiSevaApplication sApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    /**
     * @return the application context
     * @author deepaks
     */
    public static KrishiSevaApplication getInstance() {
        return sApplication;
    }

    /**
     * @return the app context
     * @author deeapaks
     */
    public static Context getAppContext() {
        return sApplication.getApplicationContext();
    }

}
