package com.fawadkhanse.mvvmdemo.models;

public class NicePlace {
    private String title;
    private String imageUrl;

    public NicePlace(String imageUrl,String title) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
