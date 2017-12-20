package com.tutexpsoft.ecommercedev.cartstore;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.fragment.CartFragment;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.query.Query;

/**
 * Created by noushad on 8/28/17.
 */

public class CartManager {

    private static CartManager sCartManager;
    private Box<CartStoreItem> cartBox;
    private Query<CartStoreItem> cartQuery;
    //    private Map<Integer, ProductItem> mCartItems;
    private Context mContext;
    private Application mApplication;

    public static CartManager getInstance(Context context) {
        if (sCartManager == null) {
            sCartManager = new CartManager(context);
        }
        return sCartManager;
    }

    private CartManager(Context context) {
        mContext = context;
//        mCartItems = new LinkedHashMap<>();


    }

    private static final String TAG = "CartManager";

    private void updateCart() {
        List<CartStoreItem> items = cartQuery.find();
        for (CartStoreItem item : items) {
            Log.d(TAG, "updateCart: " + item.getTitle());
        }
        Log.d(TAG, "updateCart: " + items.size());

    }


    public List<CartStoreItem> getCartItems() {
        List<CartStoreItem> items = cartQuery.find();
        return items;
    }

//    public ProductItem getCartItem(int id) {
//
//      return mCartItems.get(id);
//    }


    public int getCartItemPosition(int id) {

        int i = 0;
//        for (Map.Entry<Integer, ProductItem> CartItemEntry : mCartItems.entrySet()) {
//            if (CartItemEntry.getKey().equals(id)) {
//                return i;
//            }
//            i++;
//        }

        return 0;
    }

    public void addCartItem(ProductItem item, String text) {

        String img = item.getImages().get(0).getSrc();
        String title;
        String discount;
        String oldPrice;
        String currentPrice;
        int quantity;
        String size = null;
        String color = null;
        boolean onSale;

        onSale = item.getOnSale();
        title = item.getName();
        if (item.getOnSale()) {
            discount = text;
            oldPrice = item.getRegularPrice();
            currentPrice = item.getSalePrice();

        } else {
            discount = null;
            oldPrice = null;
            currentPrice = item.getPrice();
        }
        quantity = 1;
        cartBox.put(new CartStoreItem(item.getId(), title, discount, oldPrice, currentPrice, quantity, size, color, null, img, onSale));
        updateCart();

    }


    public void setApp(Application application) {
        if (mApplication == null) {
            mApplication = application;
            BoxStore boxStore = ((App) application).getBoxStore();
            cartBox = boxStore.boxFor(CartStoreItem.class);
            cartQuery = cartBox.query().order(CartStoreItem_.title).build();
            updateCart();
        }
    }

    public void removeCartItem(CartStoreItem item) {
        cartBox.remove(item);
        updateCart();
    }

    public void updateCartItemQuantity(CartStoreItem item,int quantity){
        item.setQuantity(quantity);
        cartBox.put(item);
        updateCart();
    }

    public boolean contains(Integer id) {
        try {
            return cartBox.get(id) != null;
        } catch (Exception e) {
            return false;
        }
    }

}
