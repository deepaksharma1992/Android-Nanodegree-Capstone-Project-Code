package com.example.deepaks.krishiseva.bean;


import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {
    String sectionName, webUrl, webTitle, date, authorName, imageUrl;

    public Article(String sectionName, String webUrl, String webTitle, String date, String authorName, String imageUrl) {
        this.sectionName = sectionName;
        this.webUrl = webUrl;
        this.webTitle = webTitle;
        this.date = date;
        this.authorName = authorName;
        this.imageUrl = imageUrl;
    }

    protected Article(Parcel in) {
        sectionName = in.readString();
        webUrl = in.readString();
        webTitle = in.readString();
        date = in.readString();
        authorName = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getSectionName() {
        return sectionName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getDate() {
        return date;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sectionName);
        dest.writeString(webUrl);
        dest.writeString(webTitle);
        dest.writeString(date);
        dest.writeString(authorName);
        dest.writeString(imageUrl);
    }
}
