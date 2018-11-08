package com.example.deepaks.krishiseva.util;

import android.content.Context;
import android.widget.Toast;

public class MessageUtils {
    private static Toast mToastObject;

    /**
     * @param context take the context of the caller
     * @param message the message to be shown for the toast
     * @author deepaks
     * @description method to show the toast messages
     */
    public static void showToastMessage(Context context, String message) {
        if (mToastObject != null)
            mToastObject.cancel();
        mToastObject = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        mToastObject.show();
    }
}
