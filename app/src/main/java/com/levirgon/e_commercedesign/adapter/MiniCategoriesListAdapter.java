package com.levirgon.e_commercedesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.levirgon.e_commercedesign.R;
import com.levirgon.e_commercedesign.activity.MainActivity;
import com.levirgon.e_commercedesign.model.MiniSubCategory;
import com.levirgon.e_commercedesign.viewholder.MiniCategoryVH;
import com.levirgon.e_commercedesign.viewholder.MiniSubCategoryVH;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by noushad on 11/25/17.
 */

public class MiniCategoriesListAdapter extends ExpandableRecyclerViewAdapter<MiniCategoryVH, MiniSubCategoryVH> {

    private Context mContext;

    public MiniCategoriesListAdapter(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        mContext = context;
    }

    @Override
    public MiniCategoryVH onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_categories_list_item, parent, false);
        return new MiniCategoryVH(view);
    }

    @Override
    public MiniSubCategoryVH onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_subcategory_list_item, parent, false);
        return new MiniSubCategoryVH(view);
    }

    @Override
    public void onBindChildViewHolder(MiniSubCategoryVH holder, int flatPosition, ExpandableGroup group, int childIndex) {

        final MiniSubCategory subCategory = (MiniSubCategory) group.getItems().get(childIndex);
        holder.setSubCategoryTitle(subCategory.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).onCategorySelected(subCategory.getTitle());
            }
        });

    }

    @Override
    public void onBindGroupViewHolder(MiniCategoryVH holder, int flatPosition, final ExpandableGroup group) {
        holder.setCategoryTitle(group.getTitle());
        if(group.getItemCount()==0){
            holder.hideIndicator();
            holder.setOnGroupClickListener(new OnGroupClickListener() {

                @Override
                public boolean onGroupClick(int flatPos) {
                    ((MainActivity)mContext).onCategorySelected(group.getTitle());
                    return false;
                }
            });
        }
    }
}
