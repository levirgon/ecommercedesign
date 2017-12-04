package com.levirgon.e_commercedesign.adapter;

/**
 * Created by noushad on 11/1/17.
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.levirgon.e_commercedesign.R;

import java.util.ArrayList;
import java.util.List;


public class OfferSlideShowAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;

    private static ArrayList<Integer> images;


    public OfferSlideShowAdapter(Context context) {

        this.context = context;
        images = new ArrayList<>();

    }


    @Override
    public int getCount() {

        return images.size();

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view.equals(object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.slideshow_layout, container, false);

        ImageView img = view.findViewById(R.id.imageView_id);

       // img.setImageResource(images.get(position));
        Glide.with(context).load(images.get(position)).into(img);

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);

    }

    public void add(Integer drawable) {
        images.add(drawable);
        notifyDataSetChanged();
    }

    public void addAll(List<Integer> items) {
        for (Integer item : items) {
            add(item);
        }
    }



}