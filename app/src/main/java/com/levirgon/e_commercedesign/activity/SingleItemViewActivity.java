package com.levirgon.e_commercedesign.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.levirgon.e_commercedesign.R;
import com.levirgon.e_commercedesign.adapter.productImagesSlideAdapter;
import com.levirgon.e_commercedesign.fragment.CartFragment;
import com.levirgon.e_commercedesign.fragment.CheckOutFragment;
import com.levirgon.e_commercedesign.model.CartItem;
import com.levirgon.e_commercedesign.utils.CartManager;
import com.levirgon.e_commercedesign.utils.TagManager;

import java.util.ArrayList;
import java.util.List;

public class SingleItemViewActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);
        initialize();
        setClickListeners();
    }

    private void setClickListeners() {
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addedToCart) {
                    CartManager.getInstance(SingleItemViewActivity.this).addCartItem(new CartItem());
                    cartButton.setText("Go To Cart");
                    cartButton.setTextColor(getResources().getColor(R.color.colorFButton));
                    addedToCart = true;
                } else {
                    startFragment(CartFragment.newInstance(), REPLACE);
                }
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startFragment(CheckOutFragment.newInstance(), REPLACE);
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
        cartButton = findViewById(R.id.cart_button);
        buyButton = findViewById(R.id.buy_now_button);
        mItemImage = findViewById(R.id.item_image_view);
        setUpImageSlide();
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

    private void setUpImageSlide() {
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

        List<Integer> images = new ArrayList<>();

        images.add(R.drawable.hand_bag);
        images.add(R.drawable.hand_bag_2);
        images.add(R.drawable.hand_bag_3);
        images.add(R.drawable.hand_bag_4);
        images.add(R.drawable.hand_bag_5);
        images.add(R.drawable.hand_bag_6);
        images.add(R.drawable.hand_bag_7);
        images.add(R.drawable.hand_bag_8);

        mAdapter.addAll(images);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void onItemSelected(int image) {
        mItemImage.setImageResource(image);
        //set image
    }
}
