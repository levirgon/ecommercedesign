package com.tutexpsoft.ecommercedev.Retrofit;

/**
 * Created by noushad on 12/10/17.
 */

public class EcommerceServiceProvider {
    private static final String TAG = "EcommerceServiceProvide";

    private static final EcommerceAPIinterface mService = ServiceGenerator.createService(EcommerceAPIinterface.class);

    public void getTopSellingProducts() {}

    public void getFeaturedProducts() {
    }

    public void getNewProducts() {
    }

    public void getProductsOnSale() {
    }


}
