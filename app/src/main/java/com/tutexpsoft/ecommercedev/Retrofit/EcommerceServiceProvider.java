package com.tutexpsoft.ecommercedev.Retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.event.ErrorEvent;
import com.tutexpsoft.ecommercedev.event.ItemDetailEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

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
        mService.getOnSaleProducts().enqueue(new Callback<List<ProductItem>>() {
            @Override
            public void onResponse(Call<List<ProductItem>> call, Response<List<ProductItem>> response) {
                if (response.isSuccessful()){
                    List<ProductItem> productItemList = response.body();
                    for (ProductItem productItem: productItemList){
                        productItem.getId();
                        productItem.getAttributes();
                        productItem.getAverageRating();
                        productItem.getBackordered();
                        productItem.getBackorders();
                        productItem.getBackordersAllowed();
                        productItem.getCatalogVisibility();
                        productItem.getCategories();
                        productItem.getCrossSellIds();
                        productItem.getDateCreated();
                        productItem.getDateCreatedGmt();
                        productItem.getDateModified();
                        productItem.getDateModifiedGmt();
                        productItem.getDateOnSaleFrom();
                        productItem.getDateOnSaleFromGmt();
                        productItem.getDateOnSaleTo();
                        productItem.getDateOnSaleToGmt();
                        productItem.getDefaultAttributes();
                        productItem.getDescription();
                        productItem.getDimensions();
                        productItem.getFeatured();
                        productItem.getGroupedProducts();
                        productItem.getImages();
                        productItem.getInStock();
                        productItem.getLinks();
                        productItem.getManageStock();
                        productItem.getMenuOrder();
                        productItem.getName();
                        productItem.getOnSale();
                        productItem.getParentId();
                        productItem.getPermalink();
                        productItem.getPrice();
                        productItem.getPriceHtml();
                        productItem.getPurchasable();
                        productItem.getPurchaseNote();
                        productItem.getRatingCount();
                        productItem.getRegularPrice();
                        productItem.getRelatedIds();
                        productItem.getReviewsAllowed();
                        productItem.getSalePrice();
                        productItem.getShippingClass();
                        productItem.getShippingClassId();
                        productItem.getShippingRequired();
                        productItem.getShippingTaxable();
                        productItem.getShortDescription();
                        productItem.getSku();
                        productItem.getSlug();
                        productItem.getSoldIndividually();
                        productItem.getStatus();
                        productItem.getStockQuantity();
                        productItem.getTags();
                        productItem.getTaxClass();
                        productItem.getTaxStatus();
                        productItem.getTotalSales();
                        productItem.getType();
                        productItem.getUpsellIds();
                        productItem.getVariations();
                        productItem.getWeight();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProductItem>> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed"+t.getLocalizedMessage());
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
                Log.d(TAG, "onFailure: Failed"+t.getLocalizedMessage());
            }
        });
    }

}
