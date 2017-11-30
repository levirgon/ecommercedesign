package com.levirgon.e_commercedesign.utils;

import android.content.Context;

import com.levirgon.e_commercedesign.model.CartItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by noushad on 8/28/17.
 */

public class CartManager {

    private static CartManager sCartManager;

    //private List<Crime> mCrimes;
    private Map<UUID, CartItem> mCartItems;

    public static CartManager getInstance(Context context) {
        if (sCartManager == null) {
            sCartManager = new CartManager(context);
        }
        return sCartManager;
    }

    private CartManager(Context context) {
        mCartItems = new LinkedHashMap<>();

    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(mCartItems.values());
    }

    public CartItem getCartItem(UUID id) {

        return mCartItems.get(id);
    }

    public int getCartItemPosition(UUID id) {

        int i =0;
        for(Map.Entry<UUID,CartItem> CartItemEntry : mCartItems.entrySet()){
            if (CartItemEntry.getKey().equals(id)){
                return i;
            }
            i++;
        }

        return 0;
    }

    public void addCartItem(CartItem cartItem) {
        mCartItems.put(cartItem.getId(), cartItem);
    }




}
