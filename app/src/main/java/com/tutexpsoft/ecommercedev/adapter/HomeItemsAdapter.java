package com.tutexpsoft.ecommercedev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.utils.TagManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noushad on 12/5/17.
 */

public class HomeItemsAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private int mItemType;
    private int mListType;
    List<String> DoDItems; //just for testing cause, will change its type later.
    List<String> RecomendedItems; //just for testing cause, will change its type later.
    private Context parentContext;

    public HomeItemsAdapter(Context context, int itemType, int listType) {
        mContext = context;
        mItemType = itemType;
        mListType = listType;
        DoDItems = new ArrayList<>();
        RecomendedItems = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        parentContext = parent.getContext();
        View view = null;
        if (mItemType == TagManager.BIG_ITEMS) {
            view = inflater.inflate(R.layout.home_big_item, parent, false);
            viewHolder = new HomeItemVH(view);
        }
        else if (mItemType == TagManager.SMALL_ITEMS) {
            view = inflater.inflate(R.layout.home_small_item, parent, false);
            viewHolder = new HomeItemVH(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
    }

    @Override
    public int getItemCount() {
//        return DoDItems == null ? 0 : DoDItems.size();
        return 6; //temp//standard limit
    }

    private class HomeItemVH extends RecyclerView.ViewHolder {
        public HomeItemVH(View view) {
            super(view);
        }
    }
}
