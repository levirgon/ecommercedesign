package com.levirgon.e_commercedesign.model;

/**
 * Created by s on 29/11/17.
 */

public class CartItem {
    private String productName;
    private String productSize;
    private String companyName;
    private String newPrize;
    private String oldPrize;
    private String offerPercentage;
    private String remainingOfferDate;
    private String deliveryTime;
    private int thumbnail;

    public CartItem(){
    }

    public CartItem(String productName, String productSize, String companyName, String newPrize, String oldPrize, String offerPercentage, String remainingOfferDate, String deliveryTime, int thumbnail) {
        this.productName = productName;
        this.productSize = productSize;
        this.companyName = companyName;
        this.newPrize = newPrize;
        this.oldPrize = oldPrize;
        this.offerPercentage = offerPercentage;
        this.remainingOfferDate = remainingOfferDate;
        this.deliveryTime = deliveryTime;
        this.thumbnail = thumbnail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNewPrize() {
        return newPrize;
    }

    public void setNewPrize(String newPrize) {
        this.newPrize = newPrize;
    }

    public String getOldPrize() {
        return oldPrize;
    }

    public void setOldPrize(String oldPrize) {
        this.oldPrize = oldPrize;
    }

    public String getOfferPercentage() {
        return offerPercentage;
    }

    public void setOfferPercentage(String offerPercentage) {
        this.offerPercentage = offerPercentage;
    }

    public String getRemainingOfferDate() {
        return remainingOfferDate;
    }

    public void setRemainingOfferDate(String remainingOfferDate) {
        this.remainingOfferDate = remainingOfferDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
