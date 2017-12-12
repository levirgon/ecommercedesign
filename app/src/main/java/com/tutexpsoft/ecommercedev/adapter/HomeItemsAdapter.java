package com.tutexpsoft.ecommercedev.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.Image;
import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.utils.TagManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noushad on 12/5/17.
 */

public class HomeItemsAdapter extends RecyclerView.Adapter<HomeItemsAdapter.HomeItemVH> {

    private Context mContext;
    private int mItemType;
    private int mListType;
    List<String> RecomendedItems; //just for testing cause, will change its type later.
    private Context parentContext;
    private List<ProductItem> onSaleItems;


    public class HomeItemVH extends RecyclerView.ViewHolder {
        public TextView productTitle, productOldPrice,productNewPrice;
        public ImageView productImage;
        public HomeItemVH(View view) {
            super(view);
            productTitle = (TextView) view.findViewById(R.id.dod_item_title);
            productNewPrice = (TextView) view.findViewById(R.id.dod_item_price);
            productOldPrice = (TextView) view.findViewById(R.id.dod_item_oldprice);
            productImage = (ImageView) view.findViewById(R.id.dod_item_image);
        }
    }

    public HomeItemsAdapter(Context context, int itemType, int listType) {
        mContext = context;
        mItemType = itemType;
        mListType = listType;
        onSaleItems = new ArrayList<>();
        RecomendedItems = new ArrayList<>();
    }

    @Override
    public HomeItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeItemVH viewHolder = null;
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
    public int getItemCount() {
        return onSaleItems == null ? 0 : onSaleItems.size();
         //return 5; //temp//standard limit
    }

    @Override
    public void onBindViewHolder(HomeItemVH holder, int position) {
        ProductItem productItem = onSaleItems.get(position);
        holder.productTitle.setText(productItem.getName());
        holder.productNewPrice.setText(productItem.getSalePrice());
        holder.productOldPrice.setText(productItem.getRegularPrice());
        Glide.with(mContext).load(productItem.getImages().get(0).getSrc()).into(holder.productImage);
    }

    public void addAllOSitems(List<ProductItem> productItemList) {
        for(ProductItem item : productItemList){
            add(item);
        }
    }

    private void add(ProductItem item) {
        onSaleItems.add(item);
        notifyDataSetChanged();
    }

}
