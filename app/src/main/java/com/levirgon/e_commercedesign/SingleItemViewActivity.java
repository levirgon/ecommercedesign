package com.levirgon.e_commercedesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SingleItemViewActivity extends AppCompatActivity {

    private RecyclerView mImagesList;
    private LinearLayoutManager verticalLayoutManager;
    private ImageSlideAdapter mAdapter;
    private ImageView mItemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);
        initialize();
    }

    private void initialize() {
        mItemImage = findViewById(R.id.item_image_view);
        setUpImageSlide();
    }

    private void setUpImageSlide() {
        mImagesList = findViewById(R.id.images_list);
        verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mImagesList.setLayoutManager(verticalLayoutManager);
        mImagesList.setItemAnimator(new DefaultItemAnimator());
        if (mAdapter == null) {
            mAdapter = new ImageSlideAdapter(this);
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

    public void onItemSelected(int image) {
        mItemImage.setImageResource(image);
        //set image
    }
}
