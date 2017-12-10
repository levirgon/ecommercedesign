package com.tutexpsoft.ecommercedev.Retrofit;

import android.util.Log;

import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.event.ErrorEvent;
import com.tutexpsoft.ecommercedev.event.ItemDetailEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by noushad on 12/10/17.
 */

public class EcommerceServiceProvider {
    private static final String TAG = "EcommerceServiceProvide";

    private static final EcommerceAPIinterface mService = ServiceGenerator.createService(EcommerceAPIinterface.class);

    public void getTopSellingProducts() {
    }

    public void getFeaturedProducts() {
    }

    public void getNewProducts() {
    }

    public void getProductsOnSale() {
    }

    public void getProductDetails(int id) {
        mService.getSingleProductDetail(id).enqueue(new Callback<ProductItem>() {
            @Override
            public void onResponse(Call<ProductItem> call, Response<ProductItem> response) {
                if (response.isSuccessful()) {
                    ProductItem item = response.body();
                    if (item != null) {
                        Log.d(TAG, "onResponse: Successful :" + item.toString());
                        EventBus.getDefault().post(new ItemDetailEvent(item));
                    }
                } else {
                    try {
                        Log.d(TAG, "onResponse: Failed :" + response.errorBody().string());
                        EventBus.getDefault().post(new ErrorEvent("Error Occurred!!"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductItem> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed"+t.getLocalizedMessage());
            }
        });
    }

}
