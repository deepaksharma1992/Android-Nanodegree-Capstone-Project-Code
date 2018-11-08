package com.example.deepaks.krishiseva.bean;


public class Article {
    String sectionName, webUrl, webTitle, date, authorName, imageUrl;

    public Article(String sectionName, String webUrl, String webTitle, String date, String authorName, String imageUrl) {
        this.sectionName = sectionName;
        this.webUrl = webUrl;
        this.webTitle = webTitle;
        this.date = date;
        this.authorName = authorName;
        this.imageUrl = imageUrl;
    }

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
}
