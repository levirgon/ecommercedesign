package com.levirgon.e_commercedesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.levirgon.e_commercedesign.adapters.CartViewLoaderAdapter;
import com.levirgon.e_commercedesign.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CartViewLoaderAdapter mAdapter;
    private List<CartItem> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        cartList = new ArrayList<>();
        setUpCartItems();
        initialize();
    }

    private void initialize() {
        recyclerView = (RecyclerView) findViewById(R.id.cart_item_list);
        mAdapter = new CartViewLoaderAdapter(getApplicationContext(),cartList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(mAdapter == null){
            mAdapter = new CartViewLoaderAdapter(this,cartList);
            recyclerView.setAdapter(mAdapter);
        }else {
            recyclerView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();

    }

    private void setUpCartItems() {

        int productImage [] = new int[]{
                R.drawable.hand_bag,
                R.drawable.hand_bag_2,
                R.drawable.hand_bag_3,
                R.drawable.hand_bag_4,
                R.drawable.hand_bag_5,
                R.drawable.hand_bag_6,
                R.drawable.hand_bag_7,
                R.drawable.hand_bag_8

        };

        CartItem cartItem = new CartItem("Gucci","Medium","Gucci Paris","19800","22000","10%","2","5 Dec, 2017",productImage[0]);
        cartList.add(cartItem);

        cartItem = new CartItem("D&G","Lagre","Dolce and Gavana","1800","2000","10%","1","25 Nov, 2017",productImage[1]);
        cartList.add(cartItem);

        cartItem = new CartItem("Ludo","Medium","Gucci Paris","19800","22000","10%","2","5 Dec, 2017",productImage[2]);
        cartList.add(cartItem);

        cartItem = new CartItem("Caprici","Medium","Gucci Paris","19800","22000","10%","2","5 Dec, 2017",productImage[3]);
        cartList.add(cartItem);

        cartItem = new CartItem("Chinal","Medium","Gucci Paris","19800","22000","10%","2","5 Dec, 2017",productImage[4]);
        cartList.add(cartItem);

        cartItem = new CartItem("Gucci","Medium","Gucci Paris","19800","22000","10%","2","5 Dec, 2017",productImage[5]);
        cartList.add(cartItem);

        cartItem = new CartItem("Gucci","Medium","Gucci Paris","19800","22000","10%","2","5 Dec, 2017",productImage[6]);
        cartList.add(cartItem);

        cartItem = new CartItem("Gucci","Medium","Gucci Paris","19800","22000","10%","2","5 Dec, 2017",productImage[7]);
        cartList.add(cartItem);

    }

}
