package com.levirgon.e_commercedesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.levirgon.e_commercedesign.R;
import com.levirgon.e_commercedesign.activity.SingleItemViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noushad on 11/22/17.
 */

public class productImagesSlideAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private List<Integer> images;
    private Context parentContext;

    public productImagesSlideAdapter(Context context) {
        mContext = context;
        images = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        parentContext = parent.getContext();
        View viewItem = inflater.inflate(R.layout.image_slide, parent, false);
        viewHolder = new ImageVH(viewItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Integer image = images.get(position);
        ImageVH categoryVH = (ImageVH) holder;
        categoryVH.bind(image);
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    private class ImageVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;

        public ImageVH(View view) {
            super(view);
            mImageView = view.findViewById(R.id.slide_image_item);
            view.setOnClickListener(this);
        }

        private void bind(int resId) {
           mImageView.setImageResource(resId);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            int image = images.get(pos);
            SingleItemViewActivity activity = (SingleItemViewActivity) mContext;
            activity.onItemSelected(image);
        }
    }

    public void add(int item) {
        images.add(item);
        notifyItemInserted(images.size() - 1);
    }

    public void addAll(List<Integer> items) {
        for (int item : items) {
            add(item);
        }
    }

    public void remove(int item) {
        int position = images.indexOf(item);
        if (position > -1) {
            images.remove(position);
            notifyItemRemoved(position);
        }

    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(images.get(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

}
