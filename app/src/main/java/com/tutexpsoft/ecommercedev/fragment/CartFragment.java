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
import android.widget.TextView;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.adapter.CartListAdapter;
import com.tutexpsoft.ecommercedev.cartstore.CartManager;
import com.tutexpsoft.ecommercedev.cartstore.CartStoreItem;
import com.tutexpsoft.ecommercedev.event.ItemChangedEvent;
import com.tutexpsoft.ecommercedev.utils.TagManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class CartFragment extends Fragment {

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

//    private String mParam1;
//    private String mParam2;

    private RecyclerView mCartRecyclerView;
    private CartListAdapter mAdapter;
    private Button checkoutButton;
    private List<CartStoreItem> items;
    private TextView totalText;
    private TextView deliveryChargeText;
    private TextView payableText;

    public CartFragment() {

    }


    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        checkoutButton = view.findViewById(R.id.checkout_button);
        mCartRecyclerView = view.findViewById(R.id.cart_items_list);
        totalText = view.findViewById(R.id.total_amount);
        deliveryChargeText = view.findViewById(R.id.delivery_charge);
        payableText = view.findViewById(R.id.total_payable);
        setupCartItemsList();
        setClickListener();
        items = CartManager.getInstance(getContext()).getCartItems();
        calculatePayment();

        return view;
    }

    private void calculatePayment() {
        int total = 0;
        int deliveryCharge = 0;
        for (CartStoreItem item : items) {
            total += (Integer.parseInt(item.getCurrentPrice())*item.getQuantity());
        }
        if (!items.isEmpty()) {
             deliveryCharge = TagManager.DELIVERY_CHARGE;
        }

        int payable = total + deliveryCharge;

        totalText.setText(String.valueOf(total));
        deliveryChargeText.setText(String.valueOf(deliveryCharge));
        payableText.setText(String.valueOf(payable));
    }

    private void setClickListener() {

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment checkOutFragment = new CheckOutFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.place_holder, checkOutFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }


    public void setupCartItemsList() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mCartRecyclerView.setLayoutManager(layoutManager);
        mCartRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if (mAdapter == null) {
            mAdapter = new CartListAdapter(getActivity());
            mCartRecyclerView.setAdapter(mAdapter);
        } else {
            mCartRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ItemChangedEvent event) {
        items = CartManager.getInstance(getContext()).getCartItems();
        calculatePayment();

    }


}
