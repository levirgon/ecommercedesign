package com.tutexpsoft.ecommercedev.event;

import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;

import java.util.List;

/**
 * Created by s on 12/12/17.
 */

public class OnFeaturedItemsEvent {
    private List<ProductItem> mProductItemList;

    public OnFeaturedItemsEvent(List<ProductItem> productItemList) {
        mProductItemList = productItemList;
    }

    public List<ProductItem> getProductItemList() {
        return mProductItemList;
    }
}
