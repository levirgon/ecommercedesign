package com.tutexpsoft.ecommercedev.model;

/**
 * Created by noushad on 12/3/17.
 */

public class MiniSubCategory {

    private int mId;
    private String title;

    public MiniSubCategory(int id, String title) {
        this.mId = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return mId;
    }
}
