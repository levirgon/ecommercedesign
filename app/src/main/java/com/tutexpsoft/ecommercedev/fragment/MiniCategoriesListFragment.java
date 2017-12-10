package com.tutexpsoft.ecommercedev.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutexpsoft.ecommercedev.R;
import com.tutexpsoft.ecommercedev.adapter.MiniCategoriesListAdapter;
import com.tutexpsoft.ecommercedev.model.MiniCategory;
import com.tutexpsoft.ecommercedev.utils.CategoriesManager;
import com.tutexpsoft.ecommercedev.utils.TagManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MiniCategoriesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiniCategoriesListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TAG = "filter options";

    private String mCategoriesTag;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private MiniCategoriesListAdapter mAdapter;

    public MiniCategoriesListFragment() {
    }


    public static MiniCategoriesListFragment newInstance(String tag) {
        MiniCategoriesListFragment fragment = new MiniCategoriesListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TAG, tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategoriesTag = getArguments().getString(ARG_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = initializeViews(inflater, container);
        return view;
    }

    @NonNull
    private View initializeViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_mini_categories, container, false);
        mRecyclerView = view.findViewById(R.id.mini_category_list);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {

        List<MiniCategory> categories = loadCategories();
        if (mAdapter == null) {
            mAdapter = new MiniCategoriesListAdapter(categories
                    , getActivity());
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mRecyclerView.setAdapter(mAdapter);
        }

    }

    private List<MiniCategory> loadCategories() {
        switch (mCategoriesTag) {
            case TagManager.CATEGORIE_ELECTRONICS:
                return CategoriesManager.getElectronicsList();
            case TagManager.CATEGORIE_TV_AND_APPLIANCE:
                return CategoriesManager.getElectronicsList();
            case TagManager.CATEGORIE_FASHION:
                return CategoriesManager.getElectronicsList();
            case TagManager.CATEGORIE_HOME_AND_FURNITURE:
                return CategoriesManager.getElectronicsList();
            case TagManager.CATEGORIE_BOOKS_AND_MORE:
                return CategoriesManager.getElectronicsList();
        }
        return null;
    }


}
