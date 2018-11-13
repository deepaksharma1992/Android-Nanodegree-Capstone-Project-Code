package com.example.deepaks.krishiseva.util;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtils {
    public static ProgressDialog mProgressDialog;

    /**
     * @param context        The context of activity component
     * @param loadingMessage the message to load
     * @description Method to show Progress Dialog with loading message.
     * @author deepaks
     */
    public static void showProgressDialog(Context context, String loadingMessage) {
        if (context == null)
            return;
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(loadingMessage);
            mProgressDialog.show();
        }
    }

    /**
     * @author deepaks
     * @description Method to dismiss loading message.
     */
    public static void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = null;
    }
}
