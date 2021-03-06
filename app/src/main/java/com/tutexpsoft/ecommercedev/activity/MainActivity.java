package com.tutexpsoft.ecommercedev.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.Retrofit.EcommerceServiceProvider;
import com.tutexpsoft.ecommercedev.adapter.HomeItemsAdapter;
import com.tutexpsoft.ecommercedev.adapter.OfferSlideShowAdapter;
import com.tutexpsoft.ecommercedev.event.NewProductItemsEvent;
import com.tutexpsoft.ecommercedev.event.OnFeaturedItemsEvent;
import com.tutexpsoft.ecommercedev.event.OnSaleItemsEvent;
import com.tutexpsoft.ecommercedev.event.TopSaleItemsEvent;
import com.tutexpsoft.ecommercedev.fragment.MiniCategoriesListFragment;
import com.tutexpsoft.ecommercedev.utils.TagManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends OrientationControllerActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ViewPager mViewPager;
    private OfferSlideShowAdapter mSlideShowAdapter;
    private RecyclerView mOsRecyclerView;
    private HomeItemsAdapter mOSAdapter;
    private RecyclerView mRecmndRecyclerView;
    private HomeItemsAdapter mRCMNDAdapter;
    private RecyclerView mTopSaleRecyclerView;
    private HomeItemsAdapter mTopSaleAdapter;
    private RecyclerView mNewProductsRecyclerView;
    private HomeItemsAdapter mNewProductsAdapter;
    private TextView tvMoreOnsale, tvMoreFeatured, tvMoreTopSale, tvMoreNewProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiate();
        changeSlides();
        new EcommerceServiceProvider().getProductsOnSale();
        new EcommerceServiceProvider().getFeaturedProducts();
        new EcommerceServiceProvider().getTopSellingProducts();
        new EcommerceServiceProvider().getNewProducts();

    }

    private void initiate() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        tvMoreFeatured = findViewById(R.id.more_featuredItems);
        tvMoreNewProducts = findViewById(R.id.more_newProducts);
        tvMoreTopSale = findViewById(R.id.more_topSale);
        tvMoreOnsale = findViewById(R.id.more_onSale);

        tvMoreOnsale.setOnClickListener(this);
        tvMoreTopSale.setOnClickListener(this);
        tvMoreNewProducts.setOnClickListener(this);
        tvMoreFeatured.setOnClickListener(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setupImageSlide();
        setupDODlist();
        setupRecomendedList();
        setupTopSalelist();
        setupNewProductslist();
    }

    private void setupRecomendedList() {
        mRecmndRecyclerView = findViewById(R.id.recomended_list);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecmndRecyclerView.setLayoutManager(verticalLayoutManager);
        mRecmndRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRCMNDAdapter = new HomeItemsAdapter(this, TagManager.FEATURE_PRODUCTS, TagManager.HOME_OSI);
        mRecmndRecyclerView.setAdapter(mRCMNDAdapter);

    }

    private void setupDODlist() {
        mOsRecyclerView = findViewById(R.id.dod_list);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mOsRecyclerView.setLayoutManager(verticalLayoutManager);
        mOsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mOSAdapter = new HomeItemsAdapter(this, TagManager.ON_SALE, TagManager.HOME_OSI);
        mOsRecyclerView.setAdapter(mOSAdapter);
    }

    private void setupTopSalelist() {
        mTopSaleRecyclerView = findViewById(R.id.topsale_product_list);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mTopSaleRecyclerView.setLayoutManager(verticalLayoutManager);
        mTopSaleRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTopSaleAdapter = new HomeItemsAdapter(this, TagManager.TOP_SALE, TagManager.HOME_OSI);
        mTopSaleRecyclerView.setAdapter(mTopSaleAdapter);

    }

    private void setupNewProductslist() {
        mNewProductsRecyclerView = findViewById(R.id.new_product_list);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mNewProductsRecyclerView.setLayoutManager(verticalLayoutManager);
        mNewProductsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNewProductsAdapter = new HomeItemsAdapter(this, TagManager.NEW_ITEMS, TagManager.HOME_OSI);
        mNewProductsRecyclerView.setAdapter(mNewProductsAdapter);

    }

    private void setupImageSlide() {
        mViewPager = findViewById(R.id.image_slide_view_pager);
        mSlideShowAdapter = new OfferSlideShowAdapter(this);
        loadSlideImages();
        mViewPager.setAdapter(mSlideShowAdapter);
    }

    private void loadSlideImages() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.img2);
        images.add(R.drawable.img3);
        images.add(R.drawable.img4);
        images.add(R.drawable.img5);
        images.add(R.drawable.img6);
        images.add(R.drawable.img7);
        images.add(R.drawable.img1);
        mSlideShowAdapter.addAll(images);
    }

    private void changeSlides() {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                int i = mViewPager.getCurrentItem();
                if (i == mSlideShowAdapter.getCount() - 1) {
                    i = 0;
                } else {
                    i++;
                }
                mViewPager.setCurrentItem(i, false);
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 3000, 3000);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
switch (id){

}
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String tag = null;

        switch (id) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_electronics_category:
                tag = TagManager.CATEGORIE_ELECTRONICS;
                break;
            case R.id.nav_tv_category:
                tag = TagManager.CATEGORIE_TV_AND_APPLIANCE;
                break;
            case R.id.nav_fashion_category:
//                tag = TagManager.CATEGORIE_FASHION;
                Intent categoryIntent = new Intent(this, HomeActivity.class);
                startActivity(categoryIntent);
                break;

            case R.id.nav_home_furniture_category:
                tag = TagManager.CATEGORIE_HOME_AND_FURNITURE;
                break;
            case R.id.nav_more_category:
                tag = TagManager.CATEGORIE_BOOKS_AND_MORE;
                break;
        }
        if (tag != null)
            startFragment(MiniCategoriesListFragment.newInstance(tag), tag);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.category_place_holder, fragment, tag).addToBackStack(null);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    public void onCategorySelected(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
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
    public void onSaleItemsEvent(OnSaleItemsEvent event) {
        mOSAdapter.addAllOSitems(event.getProductItemList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFeaturedItemsEvent(OnFeaturedItemsEvent event) {
        mRCMNDAdapter.addAllRFUitems(event.getProductItemList());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void topSaleItemsEvent(TopSaleItemsEvent event) {
        mTopSaleAdapter.addAllTSitems(event.getProductItemList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void newProductsItemsEvent(NewProductItemsEvent event) {
        mNewProductsAdapter.addAllNPitems(event.getProductItemList());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.more_featuredItems:
                new EcommerceServiceProvider().getFeaturedProductsMore();
                showFullList(R.id.more_featuredItems);
                break;
            case R.id.more_newProducts:
                new EcommerceServiceProvider().getNewProductsMore();
                showFullList(R.id.more_featuredItems);
                break;
            case R.id.more_onSale:
                new EcommerceServiceProvider().getOnSaleProductsMore();
                showFullList(R.id.more_featuredItems);
                break;
            case R.id.more_topSale:
                new EcommerceServiceProvider().getTopSellingProductsMore();
                showFullList(R.id.more_featuredItems);
                break;

        }

    }

    private void showFullList(int resID) {
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra(TagManager.FULL_LIST_OPERATION, resID);
        startActivity(intent);
    }
}



