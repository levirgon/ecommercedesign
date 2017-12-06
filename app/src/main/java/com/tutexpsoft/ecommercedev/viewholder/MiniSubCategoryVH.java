package com.tutexpsoft.ecommercedev.viewholder;

import android.view.View;
import android.widget.TextView;

import com.tutexpsoft.ecommercedev.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by noushad on 12/3/17.
 */

public class MiniSubCategoryVH extends ChildViewHolder {

    private TextView subCategoryTitle;

    public MiniSubCategoryVH(View itemView) {
        super(itemView);
        subCategoryTitle = itemView.findViewById(R.id.mini_sub_category_title);
    }

    public void setSubCategoryTitle(String title) {
       subCategoryTitle.setText(title);

    }
}
