package com.tutexpsoft.ecommercedev.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.Retrofit.EcommerceServiceProvider;
import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.Image;
import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.adapter.productImagesSlideAdapter;
import com.tutexpsoft.ecommercedev.event.ItemDetailEvent;
import com.tutexpsoft.ecommercedev.fragment.CartFragment;
import com.tutexpsoft.ecommercedev.fragment.CheckOutFragment;
import com.tutexpsoft.ecommercedev.utils.CartManager;
import com.tutexpsoft.ecommercedev.utils.TagManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class SingleItemViewActivity extends OrientationControllerActivity {

    private RecyclerView mImagesList;
    private LinearLayoutManager verticalLayoutManager;
    private productImagesSlideAdapter mAdapter;
    private ImageView mItemImage;
    private Button cartButton;
    private Button buyButton;
    public static final int CREATE_NEW = 1;
    public static final int REPLACE = 2;
    public String CURRENT_FRAGMENT_TAG = TagManager.CART_FRAGMENT;
    private boolean addedToCart = false;
    private NestedScrollView detailContainer;
    private LinearLayout buttonContainer;
    private ProgressBar detailProgress;
    private RatingBar mRatingBar;
    private TextView mItemTitle;
    private TextView mShopName;
    private TextView mItemStockIndicator;
    private TextView mItemDiscountText;
    private TextView mRatingCount;
    private TextView mReviewCount;
    private TextView mSaleCount;
    private TextView mOverallRating;
    private TextView mAllReviewsButton;
    private TextView mItemTitleExpanded;
    private TextView mOfferExpiryTime;
    private TextView mCurrentPriceText;
    private TextView mOldPriceText;
    private Button mShareButton;
    private Button mSimiliarButton;
    private Button mWishListButton;
    private Button mColorButton;
    private Button mSizeButton;
    private ProductItem mProductItem;
    private WebView webText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);

        int id = getIntent().getExtras().getInt(TagManager.PRODUCT_ID_KEY);
        initialize();
        setClickListeners();
        detailContainer.setVisibility(View.GONE);
        buttonContainer.setVisibility(View.GONE);
        new EcommerceServiceProvider().getProductDetails(id);
    }

    private void setClickListeners() {
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mProductItem.getInStock()) {
                    if (!addedToCart) {
                        CartManager.getInstance(SingleItemViewActivity.this).addCartItem(mProductItem);
                        cartButton.setText("Go To Cart");
                        cartButton.setTextColor(getResources().getColor(R.color.colorFButton));
                        addedToCart = true;
                    } else {
                        startFragment(CartFragment.newInstance(), REPLACE);
                    }
                } else {
                    Toast.makeText(SingleItemViewActivity.this, "Out Of Stock", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mProductItem.getInStock()) {
                    startFragment(CheckOutFragment.newInstance(), REPLACE);
                } else {
                    Toast.makeText(SingleItemViewActivity.this, "Out Of Stock", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void startFragment(Fragment fragment, int command) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (command == CREATE_NEW)
            fragmentTransaction.add(R.id.place_holder, fragment, CURRENT_FRAGMENT_TAG);
        else {
            fragmentTransaction.replace(R.id.place_holder, fragment, CURRENT_FRAGMENT_TAG).addToBackStack(null);
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

    }

    private void initialize() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        detailProgress = findViewById(R.id.detail_loading_progress);
        detailContainer = findViewById(R.id.detail_container);
        buttonContainer = findViewById(R.id.button_container);
        cartButton = findViewById(R.id.cart_button);
        buyButton = findViewById(R.id.buy_now_button);
        mItemImage = findViewById(R.id.item_image_view);
        mItemTitle = findViewById(R.id.item_title_text);
        mShopName = findViewById(R.id.item_shop_name_text);
        mItemStockIndicator = findViewById(R.id.item_stock_indicator);
        mItemDiscountText = findViewById(R.id.item_discount_text2);
        mRatingCount = findViewById(R.id.item_rating_count);
        mRatingBar = findViewById(R.id.item_rating_bar);
        mReviewCount = findViewById(R.id.review_count_text);
        mSaleCount = findViewById(R.id.item_sale_count);
        mOverallRating = findViewById(R.id.overall_rating_text);
        mAllReviewsButton = findViewById(R.id.show_review_button);
        mItemTitleExpanded = findViewById(R.id.item_title_expanded);
        mOfferExpiryTime = findViewById(R.id.offer_time_limit_text);
        mCurrentPriceText = findViewById(R.id.current_price_text);
        mOldPriceText = findViewById(R.id.old_price_text);
        mShareButton = findViewById(R.id.share_button);
        mSimiliarButton = findViewById(R.id.similiar_button);
        mWishListButton = findViewById(R.id.wishlist_button);
        mColorButton = findViewById(R.id.color_button);
        mSizeButton = findViewById(R.id.size_button);
        webText = findViewById(R.id.details_text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.all_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    private void setUpImageSlide(List<Image> images) {

        mImagesList = findViewById(R.id.images_list);
        verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mImagesList.setLayoutManager(verticalLayoutManager);
        mImagesList.setItemAnimator(new DefaultItemAnimator());
        if (mAdapter == null) {
            mAdapter = new productImagesSlideAdapter(this);
            mImagesList.setAdapter(mAdapter);
        } else {
            mImagesList.setAdapter(mAdapter);
        }

        List<String> imageLinks = new ArrayList<>();


        for (int i = 0; i < images.size(); i++) {
            imageLinks.add(images.get(i).getSrc());
        }


        mAdapter.addAll(imageLinks);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void onItemSelected(String imageLink) {
        Glide.with(this).load(imageLink).into(mItemImage);
        //set image
    }


    private void updateUI(ProductItem item) {
        mProductItem = item;
        mItemTitle.setText(item.getName());
        if (item.getOnSale()) {
            mCurrentPriceText.setText(TagManager.CURRENCY + item.getSalePrice());
            mOldPriceText.setText(item.getRegularPrice());

            int difference = Integer.parseInt(item.getRegularPrice()) - Integer.parseInt(item.getSalePrice());
            int discount = (difference * 100) / Integer.parseInt(item.getRegularPrice());

            mItemDiscountText.setText(String.valueOf(discount) + "%off");
//            mOfferExpiryTime.setText(item.getDateOnSaleTo());
        } else {
            mCurrentPriceText.setText(TagManager.CURRENCY + item.getRegularPrice());
            mOldPriceText.setVisibility(View.GONE);
            mOfferExpiryTime.setVisibility(View.GONE);
            mItemDiscountText.setText("Regular Price");

        }

        String html = item.getDescription();

        webText.loadData(html, "text/html", "utf-8");

        Glide.with(this).load(item.getImages().get(0).getSrc()).into(mItemImage);
        mOverallRating.setText(item.getAverageRating());
        mRatingCount.setText(String.valueOf(item.getRatingCount()));
        mSaleCount.setText(String.valueOf(item.getTotalSales()));
        if (item.getInStock()) {
            mItemStockIndicator.setText("In Stock");

        } else {
            mItemStockIndicator.setText("Out Of Stock");
        }

        int rating = (int) Double.parseDouble(item.getAverageRating());
        mRatingBar.setRating(rating);

        setUpImageSlide(item.getImages());

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onItemDetailEvent(ItemDetailEvent event) {
        Toast.makeText(this, "Data Recieved", Toast.LENGTH_SHORT).show();
        detailContainer.setVisibility(View.VISIBLE);
        buttonContainer.setVisibility(View.VISIBLE);
        detailProgress.setVisibility(View.GONE);
        updateUI(event.getItem());

    }
}
