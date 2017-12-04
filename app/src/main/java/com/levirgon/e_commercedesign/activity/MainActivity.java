package com.levirgon.e_commercedesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.levirgon.e_commercedesign.R;
import com.levirgon.e_commercedesign.adapter.HomeItemsAdapter;
import com.levirgon.e_commercedesign.adapter.OfferSlideShowAdapter;
import com.levirgon.e_commercedesign.fragment.MiniCategoriesListFragment;
import com.levirgon.e_commercedesign.utils.TagManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private OfferSlideShowAdapter mSlideShowAdapter;
    private RecyclerView mDodRecyclerView;
    private HomeItemsAdapter mDODAdapter;
    private RecyclerView mRecmndRecyclerView;
    private HomeItemsAdapter mRCMNDAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiate();
        changeSlides();


    }

    private void initiate() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setupImageSlide();
        setupDODlist();
        setupRecomendedList();

    }

    private void setupRecomendedList() {
        mRecmndRecyclerView = findViewById(R.id.recomended_list);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecmndRecyclerView.setLayoutManager(verticalLayoutManager);
        mRecmndRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRCMNDAdapter = new HomeItemsAdapter(this, TagManager.SMALL_ITEMS, TagManager.HOME_DOD);
        mRecmndRecyclerView.setAdapter(mRCMNDAdapter);

    }

    private void setupDODlist() {
        mDodRecyclerView = findViewById(R.id.dod_list);
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mDodRecyclerView.setLayoutManager(verticalLayoutManager);
        mDodRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mDODAdapter = new HomeItemsAdapter(this, TagManager.BIG_ITEMS, TagManager.HOME_DOD);
        mDodRecyclerView.setAdapter(mDODAdapter);

    }

    private void setupImageSlide() {
        mViewPager = findViewById(R.id.image_slide_view_pager);
        mSlideShowAdapter = new OfferSlideShowAdapter(this);
        loadSlideImages();
        mViewPager.setAdapter(mSlideShowAdapter);
    }

    private void loadSlideImages() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.img2);
        images.add(R.drawable.img3);
        images.add(R.drawable.img4);
        images.add(R.drawable.img5);
        images.add(R.drawable.img6);
        images.add(R.drawable.img7);
        images.add(R.drawable.img1);
        mSlideShowAdapter.addAll(images);
    }

    private void changeSlides() {

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                int i = mViewPager.getCurrentItem();
                if (i == mSlideShowAdapter.getCount() - 1) {
                    i = 0;
                } else {
                    i++;
                }
                mViewPager.setCurrentItem(i, false);
            }
        };

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 3000, 3000);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String tag = null;

        switch (id) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.nav_electronics_category:
                tag = TagManager.CATEGORIE_ELECTRONICS;
                break;
            case R.id.nav_tv_category:
                tag = TagManager.CATEGORIE_TV_AND_APPLIANCE;
                break;
            case R.id.nav_fashion_category:
//                tag = TagManager.CATEGORIE_FASHION;
                Intent categoryIntent = new Intent(this, HomeActivity.class);
                startActivity(categoryIntent);
                break;

            case R.id.nav_home_furniture_category:
                tag = TagManager.CATEGORIE_HOME_AND_FURNITURE;
                break;
            case R.id.nav_more_category:
                tag = TagManager.CATEGORIE_BOOKS_AND_MORE;
                break;
        }
        if (tag != null)
            startFragment(MiniCategoriesListFragment.newInstance(tag), tag);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startFragment(Fragment fragment, String tag) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.category_place_holder, fragment, tag).addToBackStack(null);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    public void onCategorySelected(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


}