package com.tutexpsoft.ecommercedev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.activity.SingleItemViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noushad on 11/22/17.
 */

public class productImagesSlideAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private List<String> imageLinks;
    private Context parentContext;

    public productImagesSlideAdapter(Context context) {
        mContext = context;
        imageLinks = new ArrayList<>();
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
        String image = imageLinks.get(position);
        ImageVH categoryVH = (ImageVH) holder;
        categoryVH.bind(image);
    }

    @Override
    public int getItemCount() {
        return imageLinks == null ? 0 : imageLinks.size();
    }

    private class ImageVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;

        public ImageVH(View view) {
            super(view);
            mImageView = view.findViewById(R.id.slide_image_item);
            view.setOnClickListener(this);
        }

        private void bind(String resId) {
            Glide.with(mContext).load(resId).into(mImageView);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            String image = imageLinks.get(pos);
            SingleItemViewActivity activity = (SingleItemViewActivity) mContext;
            activity.onItemSelected(image);
        }
    }

    public void add(String item) {
        imageLinks.add(item);
        notifyItemInserted(imageLinks.size() - 1);
    }

    public void addAll(List<String> items) {
        for (String item : items) {
            add(item);
        }
    }

    public void remove(String item) {
        int position = imageLinks.indexOf(item);
        if (position > -1) {
            imageLinks.remove(position);
            notifyItemRemoved(position);
        }

    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(imageLinks.get(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

}
