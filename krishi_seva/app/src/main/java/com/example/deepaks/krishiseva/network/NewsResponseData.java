package com.example.deepaks.krishiseva.network;


import com.example.deepaks.krishiseva.bean.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by deepak on 7/7/2017.
 */

public class NewsResponseData {
    private static final String RESPONSE = "response";
    private static final String RESULTS = "results";
    private static final String WEB_URL = "webUrl";
    private static final String WEB_TITLE = "webTitle";
    private static final String SECTION_NAME = "sectionName";
    private static final String PUBLICATION_DATE = "webPublicationDate";
    private static final String TAGS = "tags";
    private static final String FIELDS = "fields";
    private static final String THUMBNAIL = "thumbnail";


    public static ArrayList<Article> getArticleData(String responseString) {
        ArrayList<Article> articleList = new ArrayList<>();
        try {
            JSONObject rootObject = new JSONObject(responseString);
            JSONObject response = rootObject.getJSONObject(RESPONSE);
            JSONArray results = response.getJSONArray(RESULTS);
            for (int i = 0; i < results.length(); i++) {
                JSONObject articleObject = results.getJSONObject(i);
                String webUrl = articleObject.optString(WEB_URL);
                String webTitle = articleObject.optString(WEB_TITLE);
                String sectionName = articleObject.optString(SECTION_NAME);
                String publicationDate = articleObject.optString(PUBLICATION_DATE);

                String authorName = "";
                JSONArray authorArray = articleObject.getJSONArray(TAGS);
                if (authorArray.length() > 0) {
                    authorName = authorArray.getJSONObject(0).getString(WEB_TITLE);
                }
                String imageUrl = articleObject.optJSONObject(FIELDS).optString(THUMBNAIL);

                Article article = new Article(sectionName, webUrl, webTitle, publicationDate, authorName, imageUrl);
                articleList.add(article);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articleList;
    }
}
