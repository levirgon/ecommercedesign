package com.tutexpsoft.ecommercedev.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.Retrofit.EcommerceServiceProvider;
import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.adapter.HomeItemsAdapter;
import com.tutexpsoft.ecommercedev.adapter.TabLayoutAdapter;
import com.tutexpsoft.ecommercedev.event.OnSaleItemsEvent;
import com.tutexpsoft.ecommercedev.fragment.OnSaleFragment;
import com.tutexpsoft.ecommercedev.utils.TagManager;
import com.tutexpsoft.ecommercedev.viewholder.SlidingTabLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class TabLayoutActivity extends AppCompatActivity {
    private SlidingTabLayout mSlid;
    private ViewPager mViewPager;
    public ArrayList<ProductItem> onSaleItemsList,topSaleItemsList,featuredItemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        initiate();

        new EcommerceServiceProvider().getTopSellingProductsMore();
        new EcommerceServiceProvider().getFeaturedProductsMore();
        new EcommerceServiceProvider().getOnSaleProductsMore();

    }

    private void initiate(){
        mSlid = (SlidingTabLayout) findViewById(R.id.sld_toolbar);
        mViewPager = (ViewPager) findViewById(R.id.vp_tab);
        mViewPager.setAdapter(new TabLayoutAdapter(getSupportFragmentManager(),this));
        mSlid.setDistributeEvenly(true);
        mSlid.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlid.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlid.setCustomTabView(R.layout.tab_view,R.id.tv_tab);
        mSlid.setViewPager(mViewPager);

    }


}
