package com.tutexpsoft.ecommercedev.utils;

import android.content.Context;

import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by noushad on 8/28/17.
 */

public class CartManager {

    private static CartManager sCartManager;

    //private List<Crime> mCrimes;
    private Map<Integer, ProductItem> mCartItems;

    public static CartManager getInstance(Context context) {
        if (sCartManager == null) {
            sCartManager = new CartManager(context);
        }
        return sCartManager;
    }

    private CartManager(Context context) {
        mCartItems = new LinkedHashMap<>();

    }

    public ArrayList<ProductItem> getCartItems() {
        return new ArrayList<>(mCartItems.values());
    }

    public ProductItem getCartItem(int id) {

        return mCartItems.get(id);
    }

    public int getCartItemPosition(int id) {

        int i =0;
        for(Map.Entry<Integer, ProductItem> CartItemEntry : mCartItems.entrySet()){
            if (CartItemEntry.getKey().equals(id)){
                return i;
            }
            i++;
        }

        return 0;
    }

    public void addCartItem(ProductItem cartItem) {
        mCartItems.put(cartItem.getId(), cartItem);
    }




}
