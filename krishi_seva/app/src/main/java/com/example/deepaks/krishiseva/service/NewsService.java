package com.example.deepaks.krishiseva.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.example.deepaks.krishiseva.bean.Article;
import com.example.deepaks.krishiseva.network.NetworkConnection;
import com.example.deepaks.krishiseva.network.NewsResponseData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public static final String NEWS_SEVICE_ACTION = "NEWS_SERVICE_ACTION";
    public static final String NEWS_BROADCAST_ACTION = "NEWS_BROADCAST_ACTION";
    public static final String NEWS_DATA_EXTRA = "NEWS_DATA_EXTRA";

    public NewsService(String name) {
        super(name);
    }

    public NewsService() {
        super("");
    }

    public static void startNewsService(Context context, String action) {
        Intent serviceIntent = new Intent(context, NewsService.class);
        serviceIntent.setAction(action);
        context.startService(serviceIntent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent.getAction().equals(NEWS_SEVICE_ACTION)) {
            List<Article> bookList;
            try {
                String responseString = NetworkConnection.getResponseFromHttpUrl(NetworkConnection.buildUrl());
                bookList = NewsResponseData.getArticleData(responseString);
                Intent newsBroadcastIntent = new Intent(NEWS_BROADCAST_ACTION);
                newsBroadcastIntent.putParcelableArrayListExtra(NEWS_DATA_EXTRA
                        , (ArrayList<? extends Parcelable>) bookList);

                sendBroadcast(newsBroadcastIntent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
