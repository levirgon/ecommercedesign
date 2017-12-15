package com.tutexpsoft.ecommercedev.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.fragment.FeaturedFragment;
import com.tutexpsoft.ecommercedev.fragment.OnSaleFragment;
import com.tutexpsoft.ecommercedev.fragment.TopSaleFragment;

/**
 * Created by s on 15/12/17.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles ={"Top Sale","Featured","On Sale"};

    public TabLayoutAdapter(FragmentManager fm, Context c){
        super(fm);
        mContext = c;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag= null;

        if(position ==0){
            frag = new TopSaleFragment();
        }else if(position == 1){
            frag = new FeaturedFragment();
        }else if(position == 2){
            frag = new OnSaleFragment();
        }

        Bundle b = new Bundle();
        b.putInt("position", position);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    public CharSequence getPageTitle(int position){

        return titles[position];
    }
}
