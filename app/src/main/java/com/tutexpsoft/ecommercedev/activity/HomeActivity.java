package com.tutexpsoft.ecommercedev.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.adapter.CategoryLoaderAdapter;
import com.tutexpsoft.ecommercedev.model.CategoryItem;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
}
