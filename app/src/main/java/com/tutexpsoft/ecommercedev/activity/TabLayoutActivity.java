package com.tutexpsoft.ecommercedev.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.adapter.TabLayoutAdapter;
import com.tutexpsoft.ecommercedev.fragment.OnSaleFragment;
import com.tutexpsoft.ecommercedev.viewholder.SlidingTabLayout;

public class TabLayoutActivity extends AppCompatActivity {
    private SlidingTabLayout mSlid;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
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
