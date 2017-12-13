package com.tutexpsoft.ecommercedev.event;

import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;

import java.util.List;

/**
 * Created by s on 13/12/17.
 */

public class TopSaleItemsEvent {
    private List<ProductItem> mProductItemList;

    public TopSaleItemsEvent(List<ProductItem> productItemList) {
        mProductItemList = productItemList;
    }

    public List<ProductItem> getProductItemList() {
        return mProductItemList;
    }
}
