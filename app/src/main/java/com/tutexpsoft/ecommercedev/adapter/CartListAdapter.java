package com.tutexpsoft.ecommercedev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.ServerResponseModel.singleItem.ProductItem;
import com.tutexpsoft.ecommercedev.utils.CartManager;

import java.util.List;

/**
 * Created by noushad on 11/30/17.
 */

public class CartListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ProductItem> pendingItems;
    private Context parentContext;


    public CartListAdapter(Context context) {
        mContext = context;
        pendingItems = CartManager.getInstance(context).getCartItems();
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

        ProductItem item = pendingItems.get(position);
        CartItemVH cartItemVH = (CartItemVH) holder;
        cartItemVH.bind(item);
    }

    @Override
    public int getItemCount() {
        return pendingItems == null ? 0 : pendingItems.size();
    }

    private class CartItemVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView itemTitle;

        public CartItemVH(View viewItem) {
            super(viewItem);
        }

        @Override
        public void onClick(View v) {
//
        }

        public void bind(ProductItem item) {

//
        }
    }
}
