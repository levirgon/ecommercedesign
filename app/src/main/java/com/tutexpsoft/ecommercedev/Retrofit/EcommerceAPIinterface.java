package com.tutexpsoft.ecommercedev.Retrofit;

import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.utils.URLManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by noushad on 12/10/17.
 */

public interface EcommerceAPIinterface {

    //replace all the object classes with necessary response models.

    @GET(URLManager.BASE_URL + "products?orderby=top_sell&" + URLManager.SECRET_KEY)
    Call<List<ProductItem>> getTopSellingProducts();

    //replace all the object classes with necessary response models.

    @GET(URLManager.BASE_URL + "products?featured=true&" + URLManager.SECRET_KEY)
    Call<List<ProductItem>> getTopFeaturedProducts();

    //replace all the object classes with necessary response models.

    @GET(URLManager.BASE_URL + "products?" + URLManager.SECRET_KEY)
    Call<List<ProductItem>> getTopRecentProducts();

    //replace all the object classes with necessary response models.

    @GET(URLManager.BASE_URL + "products?" + URLManager.SECRET_KEY + "&on_sale=true")
    Call<List<ProductItem>> getOnSaleProducts();

    //replace all the object classes with necessary response models.

    @GET(URLManager.BASE_URL + "products/{product_id}?" + URLManager.SECRET_KEY)
    Call<ProductItem> getSingleProductDetail(@Path("product_id") int productId);

    //replace all the object classes with necessary response models.

    @GET(URLManager.BASE_URL + "products?" + URLManager.SECRET_KEY + "&category={category_id}")
    Call<List<Object>> getProductByCategory(@Path("category_id") String categoryId);

    //replace all the object classes with necessary response models.

    @GET(URLManager.BASE_URL + "products/categories?" + URLManager.SECRET_KEY)
    Call<List<Object>> getAllCategories(@Path("category_id") String categoryId);


}
