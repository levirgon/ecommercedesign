package com.levirgon.e_commercedesign.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.levirgon.e_commercedesign.R;
import com.levirgon.e_commercedesign.adapter.CategoryLoaderAdapter;
import com.levirgon.e_commercedesign.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CategoryLoaderAdapter mAdapter;
    private List<CategoryItem> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        categoryList = new ArrayList<>();
        setUpCategoryItems();
        intializeCategory();
    }

    private void intializeCategory() {
        recyclerView = (RecyclerView) findViewById(R.id.home_category_list);
        mAdapter = new CategoryLoaderAdapter(getApplicationContext(),categoryList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(mAdapter == null) {
            mAdapter = new CategoryLoaderAdapter(this,categoryList);
            recyclerView.setAdapter(mAdapter);
        }else {
            recyclerView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();

    }

    private void setUpCategoryItems() {

            int[] category = new int[]{
                    R.drawable.mans,
                    R.drawable.womens,
                    R.drawable.kids,
                    R.drawable.electronics,
                    R.drawable.home_appliances,
                    R.drawable.computer_accesorry,
                    R.drawable.makeups,
                    R.drawable.kids_toy

        };

            CategoryItem categoryItem = new CategoryItem("Man's Fashion",category[0]);
            categoryList.add(categoryItem);
            categoryItem = new CategoryItem("Women's Fashion",category[1]);
            categoryList.add(categoryItem);
            categoryItem = new CategoryItem("Kid's Fashion",category[2]);
            categoryList.add(categoryItem);
            categoryItem = new CategoryItem("Electronics",category[3]);
            categoryList.add(categoryItem);
            categoryItem = new CategoryItem("Home appliances",category[4]);
            categoryList.add(categoryItem);
            categoryItem = new CategoryItem("Accessories",category[5]);
            categoryList.add(categoryItem);
            categoryItem = new CategoryItem("Makeups",category[6]);
            categoryList.add(categoryItem);
            categoryItem = new CategoryItem("Kid's toy",category[7]);
            categoryList.add(categoryItem);

    }
}
