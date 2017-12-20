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
 * Created by s on 1/12/17.
 */

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ProductVH> {
    private Context mContext;
    private List<ProductItem> mProductsList;
    private static final int LIST_ITEM = 0;
    private static final int GRID_ITEM = 1;
    boolean isSwitchView = true;
    private boolean isLoadingAdded = false;
    private static final int LOADING = 2;


    public class ProductVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView productTitle, productPrice, productOldPrice,
                productCategory, productDiscount;
        public ImageView thumbnail;
        private ProductItem mProduct;

        public ProductVH(View view) {
            super(view);
            productTitle = (TextView) view.findViewById(R.id.productTitle);
            productPrice = (TextView) view.findViewById(R.id.product_Current_price);
            productOldPrice = (TextView) view.findViewById(R.id.product_oldPrice);
            productDiscount = (TextView) view.findViewById(R.id.more_featuredItems);
            productCategory = (TextView) view.findViewById(R.id.product_category);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            view.setOnClickListener(this);
        }


        public void bind(ProductItem product) {
            mProduct = product;
            productTitle.setText(product.getName());
            if (product.getOnSale()) {
                productPrice.setText(TagManager.CURRENCY+product.getSalePrice());
                productOldPrice.setText(product.getRegularPrice());

                if (!product.getSalePrice().equals("")) {
                    int difference = Integer.parseInt(product.getRegularPrice()) - Integer.parseInt(product.getSalePrice());
                    int discount = (difference * 100) / Integer.parseInt(product.getRegularPrice());

                    productDiscount.setText(String.valueOf(discount)+"%off");
                } else {


                }
                Glide.with(mContext).load(product.getImages().get(0).getSrc()).into(thumbnail);
            } else {
                productPrice.setText(TagManager.CURRENCY+product.getPrice());
                Glide.with(mContext).load(product.getImages().get(0).getSrc()).into(thumbnail);
                productDiscount.setVisibility(View.GONE);
                productOldPrice.setText("Regular Price");
            }
            productCategory.setText(product.getType());

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, SingleItemViewActivity.class);
            intent.putExtra(TagManager.PRODUCT_ID_KEY, mProduct.getId()); //this id will change based on item clicked
            v.getContext().startActivity(intent);
        }
    }

    public ProductTypeAdapter(Context mContext) {
        this.mContext = mContext;
        this.mProductsList = new ArrayList<>();
    }

    @Override
    public ProductVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View item;
        if (viewType == LIST_ITEM) {
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_style_view, parent, false);
        } else {
            item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.album_style_view, parent, false);
        }

        return new ProductVH(item);
    }

    @Override
    public void onBindViewHolder(ProductVH holder, int position) {

        ProductItem product = mProductsList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {

        return mProductsList == null ? 0: mProductsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isSwitchView) {
            return (position == mProductsList.size()-1 && isLoadingAdded)?LOADING:LIST_ITEM;
        } else {
            return (position == mProductsList.size()-1 && isLoadingAdded)?LOADING:GRID_ITEM;
        }
    }

    public boolean toggleItemViewType() {
        isSwitchView = !isSwitchView;
        return isSwitchView;
    }

    private void add(ProductItem item) {
        mProductsList.add(item);
        notifyItemInserted(mProductsList.size() - 1);
    }

    public void addAll(List<ProductItem> recomendedItemList) {
        for (ProductItem item : recomendedItemList) {
            add(item);
        }

    }
    public void remove(ProductItem productItem) {
        int position = mProductsList.indexOf(productItem);
        if (position > -1) {
            mProductsList.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new ProductItem());
    }
    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mProductsList.size() - 1;
        ProductItem item = getItem(position);

        if (item != null) {
            mProductsList.remove(position);
            notifyItemRemoved(position);
        }
    }
    public ProductItem getItem(int position) {
        return mProductsList.get(position);
    }

    public void loadFirstPage() {

    }

}
