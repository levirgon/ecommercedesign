package com.levirgon.e_commercedesign.activity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.levirgon.e_commercedesign.R;
import com.levirgon.e_commercedesign.adapter.ProductTypeAdapter;
import com.levirgon.e_commercedesign.model.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mGridLayoutManager,mLinearLayoutManager;
    private ProductTypeAdapter mAdapter;
    private List<Products> productsList;
    private Button sortButton, filterButton, layoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        this.setTitle("Products");
        productsList = new ArrayList<>();
        setupProducts();
        initializeProducts();
        onButtonClick();
    }

    private void initializeProducts() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sortButton = (Button) findViewById(R.id.sortButton);
        filterButton = (Button) findViewById(R.id.filterButton);
        layoutButton = (Button) findViewById(R.id.layoutButton);
        recyclerView = (RecyclerView) findViewById(R.id.product_recycler_view);
        mAdapter = new ProductTypeAdapter(getApplicationContext(),productsList);
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(mAdapter == null){
            mAdapter = new ProductTypeAdapter(this,productsList);
            recyclerView.setAdapter(mAdapter);

        }else{
            recyclerView.setAdapter(mAdapter);
        }
        //mAdapter.notifyDataSetChanged();

    }

    private void onButtonClick(){
        layoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSwitched = mAdapter.toggleItemViewType();
                if(isSwitched){
                    layoutButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.button_image_list,0,0,0);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                }else {
                    layoutButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.button_image_grid,0,0,0);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                }
                mAdapter.notifyDataSetChanged();

            }
        });
    }


    private void setupProducts() {
        int[] products = new int[]{
                R.drawable.shirt,
                R.drawable.t_shirt,
                R.drawable.tshirt,
                R.drawable.panjabi,
                R.drawable.pant,
                R.drawable.shoes,
                R.drawable.belts,
                R.drawable.sunglasses,
                R.drawable.pocket_square,
                R.drawable.suit_vest,
                R.drawable.tie,
                R.drawable.tie_pin,
                R.drawable.cufflinks,
                R.drawable.suspenders,
                R.drawable.leather_bag
        };

        Products productsItem = new Products("Shirts",26,products[0]);
        productsList.add(productsItem);
        productsItem = new Products("Polo T-Shirts",122,products[1]);
        productsList.add(productsItem);
        productsItem = new Products("T-Shirts",200,products[2]);
        productsList.add(productsItem);
        productsItem = new Products("Panjabi",12,products[3]);
        productsList.add(productsItem);
        productsItem = new Products("Pants",56,products[4]);
        productsList.add(productsItem);
        productsItem = new Products("Shoes",18,products[5]);
        productsList.add(productsItem);
        productsItem = new Products("Belts",22,products[6]);
        productsList.add(productsItem);
        productsItem = new Products("Sun Glasses",8,products[7]);
        productsList.add(productsItem);
        productsItem = new Products("Pocket Squares",23,products[8]);
        productsList.add(productsItem);
        productsItem = new Products("Suit Vests",43,products[9]);
        productsList.add(productsItem);
        productsItem = new Products("Ties",211,products[10]);
        productsList.add(productsItem);
        productsItem = new Products("Tie pins",156,products[11]);
        productsList.add(productsItem);
        productsItem = new Products("Cufflinks",82,products[12]);
        productsList.add(productsItem);
        productsItem = new Products("Suspenders",2,products[13]);
        productsList.add(productsItem);
        productsItem = new Products("Leather Bag",33,products[14]);
        productsList.add(productsItem);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_page, menu);
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

    /*private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    *//**
     * Converting dp to pixel
     *//*
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }*/
}
