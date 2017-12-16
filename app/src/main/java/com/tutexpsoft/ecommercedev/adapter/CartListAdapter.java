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
import com.tutexpsoft.ecommercedev.utils.CartManager;
import com.tutexpsoft.ecommercedev.utils.TagManager;

import java.util.ArrayList;

/**
 * Created by noushad on 11/30/17.
 */

public class CartListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<ProductItem> mCartItems;
    private Context parentContext;


    public CartListAdapter(Context context, ArrayList<ProductItem> cartItems) {
        mContext = context;
        mCartItems = CartManager.getInstance(context).getCartItems();
        mCartItems = cartItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        parentContext = parent.getContext();
        View viewItem = inflater.inflate(R.layout.cart_item, parent, false);
        viewHolder = new CartItemVH(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ProductItem item = mCartItems.get(position);
        CartItemVH cartItemVH = (CartItemVH) holder;
        cartItemVH.bind(item);
    }

    @Override
    public int getItemCount() {
        return mCartItems == null ? 0 : mCartItems.size();
    }

    private class CartItemVH extends RecyclerView.ViewHolder{
        private TextView itemTitle;
        private TextView itemDiscount;
        private TextView itemOldPrice;
        private TextView itemCurrentPrice;
        private TextView itemSize;
        private TextView itemColor;
        private TextView itemDeliveryDate;
        private ImageView mItemImage;

        public CartItemVH(View viewItem) {
            super(viewItem);
            itemTitle = viewItem.findViewById(R.id.cart_item_title);
            itemDiscount = viewItem.findViewById(R.id.cart_item_discount);
            itemOldPrice = viewItem.findViewById(R.id.cart_item_old_price);
            itemCurrentPrice = viewItem.findViewById(R.id.cart_item_current_price);
            itemSize = viewItem.findViewById(R.id.cart_item_size_text);
            itemColor = viewItem.findViewById(R.id.cart_item_color);
            itemDeliveryDate = viewItem.findViewById(R.id.delivery_date);
            mItemImage = viewItem.findViewById(R.id.cart_item_image);


        }

        public void bind(ProductItem item) {

            itemTitle.setText(item.getName());
            if (item.getOnSale()) {
                itemCurrentPrice.setText(TagManager.CURRENCY + item.getSalePrice());
                itemOldPrice.setText(item.getRegularPrice());

                int difference = Integer.parseInt(item.getRegularPrice()) - Integer.parseInt(item.getSalePrice());
                int discount = (difference * 100) / Integer.parseInt(item.getRegularPrice());

                itemDiscount.setText(String.valueOf(discount) + "%off");
            } else {
                itemCurrentPrice.setText( TagManager.CURRENCY + item.getRegularPrice());
                itemOldPrice.setVisibility(View.GONE);
                itemDiscount.setText("Regular Price");

            }

            Glide.with(mContext).load(item.getImages().get(0).getSrc()).into(mItemImage);
        }
    }
}
