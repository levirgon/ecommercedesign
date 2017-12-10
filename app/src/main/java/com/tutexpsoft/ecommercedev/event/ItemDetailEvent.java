package com.tutexpsoft.ecommercedev.event;

import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;

/**
 * Created by noushad on 12/10/17.
 */

public class ItemDetailEvent {
    private ProductItem mItem;

    public ItemDetailEvent(ProductItem item) {

        mItem = item;
    }

    public ProductItem getItem() {
        return mItem;
    }
}
