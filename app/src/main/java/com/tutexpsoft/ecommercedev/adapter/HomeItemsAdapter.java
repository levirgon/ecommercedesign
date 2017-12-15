package com.tutexpsoft.ecommercedev.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.activity.SingleItemViewActivity;
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



    private List<ProductItem> recomendedItems;
    private List<ProductItem> onSaleItems;
    private List<ProductItem> topSaleItems;
    private List<ProductItem> newProductItems;


    public class HomeItemVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView productTitle, productOldPrice, productNewPrice;
        public ImageView productImage;
        public TextView recommandedProductTitle, recommandedProductPrice;
        public ImageView recommandedProductImage;
        public TextView topSaleProductTitle, topSaleProductPrice;
        public ImageView topSaleProductImage;
        public TextView newProductTitle, newProductPrice;
        public ImageView newProductImage;
        private ProductItem mProductItem;

        public HomeItemVH(View view) {
            super(view);
            productTitle = (TextView) view.findViewById(R.id.dod_item_title);
            productNewPrice = (TextView) view.findViewById(R.id.dod_item_price);
            productOldPrice = (TextView) view.findViewById(R.id.dod_item_oldprice);
            productImage = (ImageView) view.findViewById(R.id.dod_item_image);

            recommandedProductTitle = (TextView) view.findViewById(R.id.recomended_item_title);
            recommandedProductPrice = (TextView) view.findViewById(R.id.recomended_item_price);
            recommandedProductImage = (ImageView) view.findViewById(R.id.recomended_item_image);
            topSaleProductTitle = (TextView) view.findViewById(R.id.topsale_item_title);
            topSaleProductPrice = (TextView) view.findViewById(R.id.topsale_item_price);
            topSaleProductImage = (ImageView) view.findViewById(R.id.topsale_item_image);
            newProductTitle = (TextView) view.findViewById(R.id.newproducts_item_title);
            newProductPrice = (TextView) view.findViewById(R.id.newproducts_item_price);
            newProductImage = (ImageView) view.findViewById(R.id.newproducts_item_image);

            view.setOnClickListener(this);
        }


        public void pass(ProductItem productItem) {
            mProductItem = productItem;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, SingleItemViewActivity.class);
            intent.putExtra(TagManager.PRODUCT_ID_KEY, mProductItem.getId()); //this id will change based on mProductItem clicked
            v.getContext().startActivity(intent);
        }
    }

    public HomeItemsAdapter(Context context, int itemType, int listType) {
        mContext = context;
        mItemType = itemType;
        mListType = listType;
        onSaleItems = new ArrayList<>();
        recomendedItems = new ArrayList<>();
        topSaleItems = new ArrayList<>();
        newProductItems = new ArrayList<>();
    }

    @Override
    public HomeItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeItemVH viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if (mItemType == TagManager.ON_SALE) {
            view = inflater.inflate(R.layout.home_big_item, parent, false);
            viewHolder = new HomeItemVH(view);

        }
        if (mItemType == TagManager.FEATURE_PRODUCTS) {
            view = inflater.inflate(R.layout.home_small_item, parent, false);
            viewHolder = new HomeItemVH(view);

        }
        if (mItemType == TagManager.TOP_SALE) {
            view = inflater.inflate(R.layout.home_top_sale_item, parent, false);
            viewHolder = new HomeItemVH(view);

        }
        if (mItemType == TagManager.NEW_ITEMS) {
            view = inflater.inflate(R.layout.home_new_product_items, parent, false);
            viewHolder = new HomeItemVH(view);

        }

        return viewHolder;
    }

    @Override
    public int getItemCount() {

        if (mItemType == TagManager.ON_SALE) {
            return onSaleItems.size();
        }
        if (mItemType == TagManager.FEATURE_PRODUCTS) {
            return recomendedItems.size();
        }
        if (mItemType == TagManager.TOP_SALE) {
            return topSaleItems.size();
        }
        if (mItemType == TagManager.NEW_ITEMS) {
            return newProductItems.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(HomeItemVH holder, int position) {

        ProductItem productItem = null;

        if (mItemType == TagManager.ON_SALE) {
            productItem = onSaleItems.get(position);
            holder.productTitle.setText(productItem.getName());
            holder.productNewPrice.setText("৳ " + productItem.getSalePrice());
            holder.productOldPrice.setText("৳ " + productItem.getRegularPrice());
            Glide.with(mContext).load(productItem.getImages().get(0).getSrc()).into(holder.productImage);
        }
        if (mItemType == TagManager.FEATURE_PRODUCTS) {
            productItem = recomendedItems.get(position);
            holder.recommandedProductTitle.setText(productItem.getName());
            holder.recommandedProductPrice.setText("৳ " + productItem.getRegularPrice());
            Glide.with(mContext).load(productItem.getImages().get(0).getSrc()).into(holder.recommandedProductImage);
        }
        if (mItemType == TagManager.TOP_SALE) {
            productItem = topSaleItems.get(position);
            holder.topSaleProductTitle.setText(productItem.getName());
            holder.topSaleProductPrice.setText("৳ " + productItem.getRegularPrice());
            Glide.with(mContext).load(productItem.getImages().get(0).getSrc()).into(holder.topSaleProductImage);
        }
        if (mItemType == TagManager.NEW_ITEMS) {
            productItem = newProductItems.get(position);
            holder.newProductTitle.setText(productItem.getName());
            holder.newProductPrice.setText("৳ " + productItem.getRegularPrice());
            Glide.with(mContext).load(productItem.getImages().get(0).getSrc()).into(holder.newProductImage);
        }

        holder.pass(productItem);


    }

    public void addAllOSitems(List<ProductItem> productItemList) {
        for (ProductItem item : productItemList) {
            add(item);
        }
    }

    private void add(ProductItem item) {
        onSaleItems.add(item);
        notifyDataSetChanged();
    }

    public void addAllRFUitems(List<ProductItem> recomendedItemList) {
        for (ProductItem item : recomendedItemList) {
            reAdd(item);
        }

    }

    private void reAdd(ProductItem item) {
        recomendedItems.add(item);
        notifyDataSetChanged();
    }

    public void addAllTSitems(List<ProductItem> topSaleItemList) {
        for (ProductItem item : topSaleItemList) {
            topAdd(item);
        }

    }

    private void topAdd(ProductItem item) {
        topSaleItems.add(item);
        notifyDataSetChanged();
    }

    public void addAllNPitems(List<ProductItem> newProductsItemList) {
        for (ProductItem item : newProductsItemList) {
            newProAdd(item);
        }

    }

    private void newProAdd(ProductItem item) {
        newProductItems.add(item);
        notifyDataSetChanged();
    }

    public List<ProductItem> getRecomendedItems() {
        return recomendedItems;
    }

    public List<ProductItem> getOnSaleItems() {
        return onSaleItems;
    }

    public List<ProductItem> getTopSaleItems() {
        return topSaleItems;
    }

    public List<ProductItem> getNewProductItems() {
        return newProductItems;
    }
}
