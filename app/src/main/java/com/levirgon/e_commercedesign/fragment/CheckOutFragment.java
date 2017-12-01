package com.levirgon.e_commercedesign.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.levirgon.e_commercedesign.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckOutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckOutFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    //TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    private TextView mDeliveryLocationTextView;
    private CardView mPickLocationCard;
    private GoogleApiClient mGoogleApiClient;
    private LocationManager mLocationManager;
    private final int MY_LOCATION_ACCESS = 101;
    private final int PLACE_PICKER_REQUEST = 111;

    public CheckOutFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CheckOutFragment newInstance() {
        CheckOutFragment fragment = new CheckOutFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_out, container, false);
        mDeliveryLocationTextView = view.findViewById(R.id.delivery_location_text);
        mPickLocationCard = view.findViewById(R.id.pick_location_card);

        setUpCLickListener();
        return view;
    }


    private void setUpCLickListener() {
        mPickLocationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPlace();
            }
        });
    }

    private void requestPermission() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}
                        , MY_LOCATION_ACCESS);
            }
        }
    }

    private void getPlace() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            Intent intent = builder.build(getActivity());
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == PLACE_PICKER_REQUEST) && (resultCode == Activity.RESULT_OK)) {
            Place place = PlacePicker.getPlace(getActivity(), data);
            setInformation(place);
        }
    }

    private void setInformation(Place place) {
        mDeliveryLocationTextView.setText(place.getAddress());
    }
}
