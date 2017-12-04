package com.levirgon.e_commercedesign.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.levirgon.e_commercedesign.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by noushad on 12/3/17.
 */

public class MiniCategoryVH extends GroupViewHolder {

    private TextView categoryTitle;
    private ImageView expandIndicator;

    public MiniCategoryVH(View itemView) {
        super(itemView);
        categoryTitle = itemView.findViewById(R.id.mini_category_title);
        expandIndicator = itemView.findViewById(R.id.mini_category_arrow);
    }

    public void setCategoryTitle(String title) {
        categoryTitle.setText(title);
    }

    public void hideIndicator(){
        expandIndicator.setVisibility(View.GONE);
    }


    @Override
    public void expand() {
        expandIndicator.setImageResource(R.drawable.ic_up_arrow);

    }

    @Override
    public void collapse() {
        expandIndicator.setImageResource(R.drawable.ic_down_arrow);

    }


}
