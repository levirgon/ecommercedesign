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
import com.levirgon.e_commercedesign.activity.ProductsActivity;
import com.levirgon.e_commercedesign.R;
import com.levirgon.e_commercedesign.model.CategoryItem;



import java.util.List;

/**
 * Created by s on 30/11/17.
 */

public class CategoryLoaderAdapter extends RecyclerView.Adapter<CategoryLoaderAdapter.MyViewHolder> {

    private Context mContext;
    private List<CategoryItem> categoryItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView categoryName;
        public ImageView categoryImage;

        public MyViewHolder(View view){
            super(view);

            categoryName = (TextView) view.findViewById(R.id.categoryTextView);
            categoryImage = (ImageView) view.findViewById(R.id.categoryImageView);

        }

    }

    public CategoryLoaderAdapter(Context mContext, List<CategoryItem> categoryItemList) {
        this.mContext = mContext;
        this.categoryItemList = categoryItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_single_category,parent,false);

        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        CategoryItem categoryItem = categoryItemList.get(position);
        holder.categoryName.setText(categoryItem.getCategory());
        Glide.with(mContext).load(categoryItem.getThumbnail()).into(holder.categoryImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ProductsActivity.class);
                v.getContext().startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

}