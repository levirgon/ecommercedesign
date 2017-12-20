package com.tutexpsoft.ecommercedev.cartstore;

import android.graphics.drawable.Drawable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by levirgon on 12/19/17.
 */
@Entity
public class CartStoreItem {


    @Id(assignable = true)
    long id;
    private String title;
    private String discount;
    private String oldPrice;
    private String currentPrice;
    private int quantity;
    private String size;
    private String color;
    private boolean isOnSale;

    public String getImageId() {
        return imageId;
    }

    private  String imageId;


    private String delivery_date;
//    private Drawable imageId;

    public CartStoreItem(long id, String title, String discount, String oldPrice, String currentPrice,
                         int quantity, String size, String color, String delivery_date, String imageId, boolean onSale) {
        this.id = id;
        this.title = title;
        this.discount = discount;
        this.oldPrice = oldPrice;
        this.currentPrice = currentPrice;
        quantity = quantity;
        this.size = size;
        this.color = color;
        this.delivery_date = delivery_date;
        this.imageId = imageId;
        isOnSale = onSale;
    }

    public CartStoreItem() {

    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDiscount() {
        return discount;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

//
//    public Drawable getImageId() {
//        return imageId;
//    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        quantity = quantity;
    }

    public boolean isOnSale() {
        return isOnSale;
    }
}
