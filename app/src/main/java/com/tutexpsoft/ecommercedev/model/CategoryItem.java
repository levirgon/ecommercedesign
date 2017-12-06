package com.tutexpsoft.ecommercedev.model;

/**
 * Created by s on 30/11/17.
 */

public class CategoryItem {
    private String category;
    private int thumbnail;

    public CategoryItem(){
    }

    public CategoryItem(String category, int thumbnail) {
        this.category = category;
        this.thumbnail = thumbnail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
