package com.levirgon.e_commercedesign.model;

/**
 * Created by s on 1/12/17.
 */

public class Products {
    private String productType;
    private int numOfProducts;
    private int thumbnail;

    public Products(){

    }

    public Products(String productType, int numOfProducts, int thumbnail) {
        this.productType = productType;
        this.numOfProducts = numOfProducts;
        this.thumbnail = thumbnail;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public void setNumOfProducts(int numOfProducts) {
        this.numOfProducts = numOfProducts;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
