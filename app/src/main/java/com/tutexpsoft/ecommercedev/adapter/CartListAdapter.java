package com.tutexpsoft.ecommercedev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.cartstore.CartManager;
import com.tutexpsoft.ecommercedev.cartstore.CartStoreItem;
import com.tutexpsoft.ecommercedev.event.ItemChangedEvent;
import com.tutexpsoft.ecommercedev.utils.TagManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by noushad on 11/30/17.
 */

public class CartListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<CartStoreItem> mCartItems;
    private Context parentContext;


    public CartListAdapter(Context context) {
        mContext = context;
        mCartItems = CartManager.getInstance(context).getCartItems();
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

        CartStoreItem item = mCartItems.get(position);
        CartItemVH cartItemVH = (CartItemVH) holder;
        cartItemVH.bind(item);
    }

    @Override
    public int getItemCount() {
        return mCartItems == null ? 0 : mCartItems.size();
    }

    private class CartItemVH extends RecyclerView.ViewHolder {
        private TextView itemTitle;
        private TextView itemDiscount;
        private TextView itemOldPrice;
        private TextView itemCurrentPrice;
        private TextView itemSize;
        private TextView itemColor;
        private TextView itemDeliveryDate;
        private TextView quantityText;
        private ImageView mItemImage;
        private Button removeButton;
        private Button addWishButton;
        private CartStoreItem mItem;
        private ImageButton increaseBUtton;
        private ImageButton decreaseButton;

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
            removeButton = viewItem.findViewById(R.id.remove_button);
            addWishButton = viewItem.findViewById(R.id.wish_add_button);
            quantityText = viewItem.findViewById(R.id.item_quantity);
            increaseBUtton = viewItem.findViewById(R.id.increase_button);
            decreaseButton = viewItem.findViewById(R.id.decrease_button);


            increaseBUtton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(quantityText.getText().toString());
                    ++quantity;
                    quantityText.setText(String.valueOf(quantity));
                    CartManager.getInstance(mContext).updateCartItemQuantity(mItem,quantity);
                    EventBus.getDefault().post(new ItemChangedEvent());
                }
            });

            decreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(quantityText.getText().toString());
                    if (quantity > 1) {
                        --quantity;
                        quantityText.setText(String.valueOf(quantity));
                        CartManager.getInstance(mContext).updateCartItemQuantity(mItem,quantity);
                        EventBus.getDefault().post(new ItemChangedEvent());

                    }
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CartManager.getInstance(mContext).removeCartItem(mItem);
                    int currPosition = mCartItems.indexOf(mItem);
                    mCartItems.remove(currPosition);
                    notifyItemRemoved(currPosition);
                    EventBus.getDefault().post(new ItemChangedEvent());

                }
            });

            addWishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "YOUR WISH HAS BEEN GRANTED", Toast.LENGTH_SHORT).show();
                }
            });

        }

        public void bind(CartStoreItem item) {
            mItem = item;

            itemTitle.setText(item.getTitle());
            if (item.isOnSale()) {
                itemCurrentPrice.setText(TagManager.CURRENCY + item.getCurrentPrice());
                itemOldPrice.setText(item.getOldPrice());
                itemDiscount.setText(item.getDiscount());
            } else {
                itemCurrentPrice.setText(TagManager.CURRENCY + item.getCurrentPrice());
                itemOldPrice.setVisibility(View.GONE);
                itemDiscount.setText("Regular Price");

            }
            quantityText.setText(String.valueOf(item.getQuantity()));

            Glide.with(mContext).load(item.getImageId()).into(mItemImage);
        }
    }
}
