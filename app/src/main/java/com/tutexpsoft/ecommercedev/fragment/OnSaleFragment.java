package com.tutexpsoft.ecommercedev.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.Retrofit.EcommerceServiceProvider;
import com.tutexpsoft.ecommercedev.adapter.HomeItemsAdapter;
import com.tutexpsoft.ecommercedev.event.OnSaleItemsEvent;
import com.tutexpsoft.ecommercedev.utils.TagManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class OnSaleFragment extends Fragment {
    private RecyclerView mOsRecyclerView;
    private HomeItemsAdapter mOSAdapter;


    public OnSaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        new EcommerceServiceProvider().getOnSaleProductsMore();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_sale, container, false);


        mOsRecyclerView = (RecyclerView) view.findViewById(R.id.product_loader_recycler_view);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mOsRecyclerView.setLayoutManager(verticalLayoutManager);
        mOsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mOSAdapter = new HomeItemsAdapter(getActivity().getApplicationContext(), TagManager.ON_SALE, TagManager.HOME_OSI);
        mOsRecyclerView.setAdapter(mOSAdapter);
        return view;
    }





    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSaleItemsEvent(OnSaleItemsEvent event) {
        mOSAdapter.addAllOSitems(event.getProductItemList());
    }
}
