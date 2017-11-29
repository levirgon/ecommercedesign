package com.levirgon.e_commercedesign.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.levirgon.e_commercedesign.R;
import com.levirgon.e_commercedesign.model.CartItem;

import java.util.List;

/**
 * Created by s on 29/11/17.
 */

public class CartViewLoaderAdapter extends RecyclerView.Adapter<CartViewLoaderAdapter.MyViewHolder> {
    private Context mContext;
    private List<CartItem> cartItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView productName,productSize,companyName,newPrize,oldPrize,offerPercent,remainOffer,deliveryTime;
        public ImageView productImage;

        public MyViewHolder(View view){
            super(view);
            productName = (TextView) view.findViewById(R.id.productNameTextView);
            productSize = (TextView) view.findViewById(R.id.sizeTextView);
            companyName = (TextView) view.findViewById(R.id.companyTextView);
            newPrize = (TextView) view.findViewById(R.id.nowPrizeTextView);
            oldPrize = (TextView) view.findViewById(R.id.oldPrizeTextView);
            offerPercent = (TextView) view.findViewById(R.id.offerTextView);
            remainOffer = (TextView) view.findViewById(R.id.remainOfferTextView);
            deliveryTime = (TextView) view.findViewById(R.id.deliveryTimeTextView);
            productImage = (ImageView) view.findViewById(R.id.productImageView);
        }
    }

    public CartViewLoaderAdapter(Context mContext, List<CartItem> cartItemList) {

        this.mContext = mContext;
        this.cartItemList = cartItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_cart_item_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        CartItem cartItem = cartItemList.get(position);
        holder.productName.setText(cartItem.getProductName());
        holder.productSize.setText(cartItem.getProductSize());
        holder.companyName.setText(cartItem.getCompanyName());
        holder.newPrize.setText(cartItem.getNewPrize());
        holder.oldPrize.setText(cartItem.getOldPrize());
        holder.offerPercent.setText(cartItem.getOfferPercentage());
        holder.remainOffer.setText(cartItem.getRemainingOfferDate());
        holder.deliveryTime.setText(cartItem.getDeliveryTime());

        Glide.with(mContext).load(cartItem.getThumbnail()).into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }
}
