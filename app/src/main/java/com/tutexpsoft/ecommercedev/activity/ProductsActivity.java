package com.tutexpsoft.ecommercedev.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.adapter.ProductTypeAdapter;
import com.tutexpsoft.ecommercedev.event.NewProductItemsEvent;
import com.tutexpsoft.ecommercedev.event.OnFeaturedItemsEvent;
import com.tutexpsoft.ecommercedev.event.OnSaleItemsEvent;
import com.tutexpsoft.ecommercedev.event.TopSaleItemsEvent;
import com.tutexpsoft.ecommercedev.utils.PaginationScrollListener;
import com.tutexpsoft.ecommercedev.utils.TagManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class ProductsActivity extends OrientationControllerActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mGridLayoutManager,mLinearLayoutManager;
    private ProductTypeAdapter mTypeAdapter;
    private Button sortButton, filterButton, layoutButton;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 16;
    private int currentPage = PAGE_START;
    List<ProductItem> productItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        int id = getIntent().getExtras().getInt(TagManager.FULL_LIST_OPERATION);
        this.setTitle("Products");
        setupProducts(id);
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
        mTypeAdapter = new ProductTypeAdapter(getApplicationContext());
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(mTypeAdapter == null){
            mTypeAdapter = new ProductTypeAdapter(this);
            recyclerView.setAdapter(mTypeAdapter);
            /*recyclerView.addOnScrollListener(new PaginationScrollListener(mLinearLayoutManager) {
                @Override
                protected void loadMoreItems() {
                    isLoading = true;
                    currentPage += 1;
                    loadNextPage();
                }

                @Override
                public int getTotalPageCount() {
                    return TOTAL_PAGES;
                }

                @Override
                public boolean isLastPage() {
                    return isLastPage;
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });
            loadFirstPage();*/

        }else{
            recyclerView.setAdapter(mTypeAdapter);
            /*recyclerView.addOnScrollListener(new PaginationScrollListener(mLinearLayoutManager) {
                @Override
                protected void loadMoreItems() {
                    isLoading = true;
                    currentPage += 1;
                    loadNextPage();
                }

                @Override
                public int getTotalPageCount() {
                    return TOTAL_PAGES;
                }

                @Override
                public boolean isLastPage() {
                    return isLastPage;
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });
            loadFirstPage();*/

        }
    }

    private void loadNextPage() {

    }
    private void loadFirstPage(){

    }


    private void onButtonClick(){
        layoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSwitched = mTypeAdapter.toggleItemViewType();
                if(isSwitched){
                    layoutButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_grid,0,0,0);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                }else {
                    layoutButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_list,0,0,0);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                }
                mTypeAdapter.notifyDataSetChanged();

            }
        });
    }


    private void setupProducts(int id) {
        switch (id) {
            case R.id.more_featuredItems:
                break;
            case R.id.more_newProducts:
                break;
            case R.id.more_onSale:
                break;
            case R.id.more_topSale:
                break;

        }

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
        mTypeAdapter.addAll(event.getProductItemList());
        mTypeAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFeaturedItemsEvent(OnFeaturedItemsEvent event) {
        mTypeAdapter.addAll(event.getProductItemList());
        mTypeAdapter.notifyDataSetChanged();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void topSaleItemsEvent(TopSaleItemsEvent event) {
        mTypeAdapter.addAll(event.getProductItemList());
        mTypeAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void newProductsItemsEvent(NewProductItemsEvent event) {
        mTypeAdapter.addAll(event.getProductItemList());
        mTypeAdapter.notifyDataSetChanged();
    }

}
