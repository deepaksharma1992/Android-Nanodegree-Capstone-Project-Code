package com.example.deepaks.krishiseva.util;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.deepaks.krishiseva.R;

import java.util.Random;

public class ColorUtils {

    public static int getRandomColorCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(15) + 1;

        switch (randomNumber) {
            case 1:
                return R.color.color_red_500;
            case 2:
                return R.color.color_pink_500;
            case 3:
                return R.color.color_purple_500;
            case 4:
                return R.color.color_deep_purple_500;
            case 5:
                return R.color.color_indigo_500;
            case 6:
                return R.color.color_blue_500;
            case 7:
                return R.color.color_light_blue_500;
            case 8:
                return R.color.color_cyan_500;
            case 9:
                return R.color.color_teal_500;
            case 10:
                return R.color.color_green_500;
            case 11:
                return R.color.color_lime_500;
            case 12:
                return R.color.color_amber_500;
            case 13:
                return R.color.color_orange_500;
            case 14:
                return R.color.color_gray_500;
            case 15:
                return R.color.color_blue_gray_500;
            default:
                return R.color.color_deep_orange_500;
        }
    }

    /**
     * @param context the context for the activity components
     * @param view    for the application
     * @author deepaks
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setDrawableColor(Context context, View view) {
        GradientDrawable drawable = (GradientDrawable) view.getBackground().mutate();
        //set color
        drawable.setColor(ContextCompat.getColorStateList(context, getRandomColorCode()));
    }
}

