package com.levirgon.e_commercedesign.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.levirgon.e_commercedesign.R;
import com.levirgon.e_commercedesign.activity.SingleItemViewActivity;
import com.levirgon.e_commercedesign.model.Products;

import java.util.List;

/**
 * Created by s on 1/12/17.
 */

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.MyViewHolder> {
    private Context mContext;
    private List<Products> productsList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productTitle, productCount;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);

            productTitle = (TextView) view.findViewById(R.id.productTitle);
            productCount = (TextView) view.findViewById(R.id.productCount);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);

        }
    }

    public ProductTypeAdapter(Context mContext, List<Products> productsList) {
        this.mContext = mContext;
        this.productsList = productsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_style_view, parent, false);

        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Products products = productsList.get(position);
        holder.productTitle.setText(products.getProductType());
        holder.productCount.setText(products.getNumOfProducts()+" Pices");
        Glide.with(mContext).load(products.getThumbnail()).into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,SingleItemViewActivity.class);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }
}
