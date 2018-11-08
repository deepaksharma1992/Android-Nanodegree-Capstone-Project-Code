package com.example.deepaks.krishiseva.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {

    public static void setImageFromUrl(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                /* .apply(new RequestOptions()
                         .centerCrop()
                         .error(R.drawable.movie_default)
                         .placeholder(R.drawable.movie_default))*/
                .into(imageView);
    }
}
