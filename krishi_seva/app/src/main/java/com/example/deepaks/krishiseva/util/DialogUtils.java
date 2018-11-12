package com.example.deepaks.krishiseva.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.deepaks.krishiseva.R;

public class DialogUtils {
    public static ProgressDialog sProgressBarDialog;

    public static void showProgressBar(Context context) {
        sProgressBarDialog = new ProgressDialog(context);
        sProgressBarDialog.setMessage(context.getString(R.string.loading_message));
        sProgressBarDialog.show();
    }

    public static void dismissProgressBar() {
        if (sProgressBarDialog.isShowing()) {
            sProgressBarDialog.dismiss();
        }
    }
}
