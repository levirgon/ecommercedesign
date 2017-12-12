package com.tutexpsoft.ecommercedev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tutexpsoft.ecommercedev.R;
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
    List<ProductItem> recomendedItems; //just for testing cause, will change its type later.
    private Context parentContext;
    private List<ProductItem> onSaleItems;


    public class HomeItemVH extends RecyclerView.ViewHolder {
        public TextView productTitle, productOldPrice,productNewPrice;
        public ImageView productImage;
        public TextView recommandedProductTitle, recommandedProductPrice;
        public ImageView recommandedProductImage;
        public HomeItemVH(View view) {
            super(view);
            productTitle = (TextView) view.findViewById(R.id.dod_item_title);
            productNewPrice = (TextView) view.findViewById(R.id.dod_item_price);
            productOldPrice = (TextView) view.findViewById(R.id.dod_item_oldprice);
            productImage = (ImageView) view.findViewById(R.id.dod_item_image);

            recommandedProductTitle = (TextView) view.findViewById(R.id.recomended_item_title);
            recommandedProductPrice = (TextView) view.findViewById(R.id.recomended_item_price);
            recommandedProductImage = (ImageView) view.findViewById(R.id.recomended_item_image);

        }
    }

    public HomeItemsAdapter(Context context, int itemType, int listType) {
        mContext = context;
        mItemType = itemType;
        mListType = listType;
        onSaleItems = new ArrayList<>();
        recomendedItems = new ArrayList<>();
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
       if (mItemType == TagManager.SMALL_ITEMS) {
           view = inflater.inflate(R.layout.home_small_item, parent, false);
           viewHolder = new HomeItemVH(view);

        }

        return viewHolder;
    }
    @Override
    public int getItemCount() {

        if (mItemType == TagManager.HOME_OSI) {
            return onSaleItems.size();
        }
        if (mItemType == TagManager.HOME_FI) {
            return recomendedItems.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(HomeItemVH holder, int position) {
        if (mItemType == TagManager.BIG_ITEMS) {
            ProductItem productItem = onSaleItems.get(position);
            holder.productTitle.setText(productItem.getName());
            holder.productNewPrice.setText("৳ " + productItem.getSalePrice());
            holder.productOldPrice.setText("৳ " + productItem.getRegularPrice());
            Glide.with(mContext).load(productItem.getImages().get(0).getSrc()).into(holder.productImage);
        }
        if (mItemType == TagManager.SMALL_ITEMS) {
            ProductItem productItem1 = recomendedItems.get(position);
            holder.recommandedProductTitle.setText(productItem1.getName());
            holder.recommandedProductPrice.setText("৳ " + productItem1.getRegularPrice());
            Glide.with(mContext).load(productItem1.getImages().get(0).getSrc()).into(holder.recommandedProductImage);
        }
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

    public void addAllRFUitems(List<ProductItem> recomendedItemList){
        for (ProductItem item: recomendedItemList){
            reAdd(item);
        }

    }
    private void reAdd(ProductItem item){
        recomendedItems.add(item);
        notifyDataSetChanged();
    }

}
