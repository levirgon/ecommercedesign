package com.levirgon.e_commercedesign.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.levirgon.e_commercedesign.R;
import com.levirgon.e_commercedesign.adapter.MiniCategoriesListAdapter;
import com.levirgon.e_commercedesign.utils.TagManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MiniCategoriesListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MiniCategoriesListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiniCategoriesListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TAG = "filter options";

    private String mCategoriesTag;

    private OnFragmentInteractionListener mListener;
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
        mListener = (OnFragmentInteractionListener) getActivity();
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new MiniCategoriesListAdapter(getActivity().getApplicationContext(), mListener, mCategoriesTag);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mRecyclerView.setAdapter(mAdapter);
        }
        loadFilterOptions();
    }

    private void loadFilterOptions() {
        List<String> options = new ArrayList<>();
        switch (mCategoriesTag) {
            case TagManager.CATEGORIE_ELECTRONICS:
                options = getElectronicsList();
                break;
            case TagManager.CATEGORIE_TV_AND_APPLIANCE:
                options = getElectronicsList();
                break;
            case TagManager.CATEGORIE_FASHION:
                options = getElectronicsList();
                break;
            case TagManager.CATEGORIE_HOME_AND_FURNITURE:
                options = getElectronicsList();
                break;
            case TagManager.CATEGORIE_BOOKS_AND_MORE:
                options = getElectronicsList();
                break;
        }
        mAdapter.addAll(options);
    }



    private List<String> getElectronicsList() {
        List<String> locations = new ArrayList<>();
        locations.add("Mobile");
        locations.add("Mobile Accessories");
        locations.add("Cameras & Accessories");
        locations.add("Audio & Video");
        locations.add("Smart Watches & Wearables");
        locations.add("Laptops");
        locations.add("Desktop PCs");
        locations.add("Gaming And Accessories");
        locations.add("Tablets");
        locations.add("Computer Accessories");
        locations.add("Televisions");
        locations.add("Personal HealthCare");
        locations.add("Printer, Monitors And More");
        return locations;

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String text, String tag);
    }
}
