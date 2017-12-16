package com.tutexpsoft.ecommercedev.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.adapter.CartListAdapter;
import com.tutexpsoft.ecommercedev.utils.CartManager;


public class CartFragment extends Fragment {

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

//    private String mParam1;
//    private String mParam2;

    private RecyclerView mCartRecyclerView;
    private CartListAdapter mAdapter;
    private Button checkoutButton;

    public CartFragment() {
        // Required empty public constructor
    }


    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        checkoutButton = view.findViewById(R.id.checkout_button);
        setupCartItemsList(view);
        setClickListener();
        return view;
    }

    private void setClickListener() {

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment checkOutFragment = new CheckOutFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.place_holder, checkOutFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }



    private void setupCartItemsList(View view) {
        mCartRecyclerView = view.findViewById(R.id.cart_items_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mCartRecyclerView.setLayoutManager(layoutManager);
        mCartRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if (mAdapter == null) {
            mAdapter = new CartListAdapter(getActivity(), CartManager.getInstance(getActivity()).getCartItems());
            mCartRecyclerView.setAdapter(mAdapter);
        } else {
            mCartRecyclerView.setAdapter(mAdapter);
        }
    }




}
