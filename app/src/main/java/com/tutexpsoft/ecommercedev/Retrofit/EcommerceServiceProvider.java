package com.tutexpsoft.ecommercedev.Retrofit;

import android.util.Log;

import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.event.ErrorEvent;
import com.tutexpsoft.ecommercedev.event.ItemDetailEvent;
import com.tutexpsoft.ecommercedev.event.NewProductItemsEvent;
import com.tutexpsoft.ecommercedev.event.OnFeaturedItemsEvent;
import com.tutexpsoft.ecommercedev.event.OnSaleItemsEvent;
import com.tutexpsoft.ecommercedev.event.TopSaleItemsEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by noushad on 12/10/17.
 */

public class EcommerceServiceProvider {
    private static final String TAG = "EcommerceProvider";

    private static final EcommerceAPIinterface mService = ServiceGenerator.createService(EcommerceAPIinterface.class);

    public void getTopSellingProducts() {
        mService.getTopSellingProducts().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if(response.isSuccessful()){
                    List<ProductItem> productItemList = response.body();
                    if (productItemList != null) {
                        Log.d(TAG, "onResponse: Successful :" + response.body().toString());
                        EventBus.getDefault().post(new TopSaleItemsEvent(productItemList));
                    }else {
                        try {
                            Log.d(TAG, "onResponse: Failed :" + response.errorBody().string());
                            EventBus.getDefault().post(new ErrorEvent("Error Occurred!!"));
                        } catch (IOException e) {
                            e.printStackTrace();
                            EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed" + t.getLocalizedMessage());
            }
        });
    }

    public void getFeaturedProducts() {
        mService.getTopFeaturedProducts().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if(response.isSuccessful()){
                    List<ProductItem> productItemList = response.body();
                    if (productItemList != null) {
                        Log.d(TAG, "onResponse: Successful :" + response.body().toString());
                        EventBus.getDefault().post(new OnFeaturedItemsEvent(productItemList));
                    }else {
                        try {
                            Log.d(TAG, "onResponse: Failed :" + response.errorBody().string());
                            EventBus.getDefault().post(new ErrorEvent("Error Occurred!!"));
                        } catch (IOException e) {
                            e.printStackTrace();
                            EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed" + t.getLocalizedMessage());
            }
        });
    }

    public void getNewProducts() {
        mService.getTopRecentProducts().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if(response.isSuccessful()){
                    List<ProductItem> productItemList = response.body();
                    if (productItemList != null) {
                        Log.d(TAG, "onResponse: Successful :" + response.body().toString());
                        EventBus.getDefault().post(new NewProductItemsEvent(productItemList));
                    }else {
                        try {
                            Log.d(TAG, "onResponse: Failed :" + response.errorBody().string());
                            EventBus.getDefault().post(new ErrorEvent("Error Occurred!!"));
                        } catch (IOException e) {
                            e.printStackTrace();
                            EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed" + t.getLocalizedMessage());
            }
        });
    }

    public void getProductsOnSale() {
        mService.getOnSaleProducts().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if (response.isSuccessful()) {
                    List<ProductItem> productItemList = response.body();
                    if (productItemList != null) {
                        Log.d(TAG, "onResponse: Successful :" + response.body().toString());
                        EventBus.getDefault().post(new OnSaleItemsEvent(productItemList));
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
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed" + t.getLocalizedMessage());
            }
        });

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
                Log.d(TAG, "onFailure: Failed" + t.getLocalizedMessage());
            }
        });
    }

    public void getFeaturedProductsMore(){
        mService.getFeaturedProductsMore().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if(response.isSuccessful()){
                    List<ProductItem> productItemList = response.body();
                    if (productItemList != null) {
                        Log.d(TAG, "onResponse: Successful :" + response.body().toString());
                        EventBus.getDefault().post(new OnFeaturedItemsEvent(productItemList));
                    }else {
                        try {
                            Log.d(TAG, "onResponse: Failed :" + response.errorBody().string());
                            EventBus.getDefault().post(new ErrorEvent("Error Occurred!!"));
                        } catch (IOException e) {
                            e.printStackTrace();
                            EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed" + t.getLocalizedMessage());
            }
        });
    }

    public void getTopSellingProductsMore(){
        mService.getTopRecentProductsMore().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if(response.isSuccessful()){
                    List<ProductItem> productItemList = response.body();
                    if (productItemList != null) {
                        Log.d(TAG, "onResponse: Successful :" + response.body().toString());
                        EventBus.getDefault().post(new TopSaleItemsEvent(productItemList));
                    }else {
                        try {
                            Log.d(TAG, "onResponse: Failed :" + response.errorBody().string());
                            EventBus.getDefault().post(new ErrorEvent("Error Occurred!!"));
                        } catch (IOException e) {
                            e.printStackTrace();
                            EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed" + t.getLocalizedMessage());
            }
        });
    }

    public void getOnSaleProductsMore(){
        mService.getOnSaleProductsMore().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if (response.isSuccessful()) {
                    List<ProductItem> productItemList = response.body();
                    if (productItemList != null) {
                        Log.d(TAG, "onResponse: Successful :" + response.body().toString());
                        EventBus.getDefault().post(new OnSaleItemsEvent(productItemList));
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
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed" + t.getLocalizedMessage());
            }
        });
    }

}
