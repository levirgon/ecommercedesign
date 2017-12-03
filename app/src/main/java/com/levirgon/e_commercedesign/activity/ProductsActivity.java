package com.levirgon.e_commercedesign.activity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
    private Button sortButton, filterButton;
    private ImageButton layoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        this.setTitle("Products");
        productsList = new ArrayList<>();
        setupProducts();
        initializeProducts();
    }

    private void initializeProducts() {
        sortButton = (Button) findViewById(R.id.sortButton);
        filterButton = (Button) findViewById(R.id.filterButton);
        layoutButton = (ImageButton) findViewById(R.id.layoutButton);
        recyclerView = (RecyclerView) findViewById(R.id.product_recycler_view);
        mAdapter = new ProductTypeAdapter(getApplicationContext(),productsList);
        mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(mAdapter == null){
            mAdapter = new ProductTypeAdapter(this,productsList);
            recyclerView.setAdapter(mAdapter);

        }else{
            recyclerView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();

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

    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
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

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
