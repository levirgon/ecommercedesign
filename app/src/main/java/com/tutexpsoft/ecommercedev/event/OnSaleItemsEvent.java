package com.tutexpsoft.ecommercedev.event;

import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;

import java.util.List;

/**
 * Created by noushad on 12/12/17.
 */

public class OnSaleItemsEvent {
    private List<ProductItem> mProductItemList;

    public OnSaleItemsEvent(List<ProductItem> productItemList) {
        mProductItemList = productItemList;
    }

    public List<ProductItem> getProductItemList() {
        return mProductItemList;
    }
}
