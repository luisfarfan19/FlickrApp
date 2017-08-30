package com.example.luisf.flickrapp;

/**
 * Created by luisf on 29/08/2017.
 */

public class Category {
    private String categoryName;
    private String categoryPhoto;

    public Category(String categoryName, String categoryPhoto){
        this.categoryName = categoryName;
        this.categoryPhoto = categoryPhoto;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPhoto() {
        return categoryPhoto;
    }

    public void setCategoryPhoto(String categoryPhoto) {
        this.categoryPhoto = categoryPhoto;
    }
}
