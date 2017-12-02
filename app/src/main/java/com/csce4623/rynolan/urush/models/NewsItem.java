package com.csce4623.rynolan.urush.models;

public class NewsItem {
    private String title;
    private String description;
    private String date;

    public NewsItem(String date, String description, String title) {
        this.date = date;
        this.description = description;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
