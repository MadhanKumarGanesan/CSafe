package com.madcodes.CSafe.ui.nearbyStations;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.madcodes.CSafe.R;
import com.madcodes.CSafe.Utils.GPSTracker;
import com.madcodes.CSafe.WebViewActivity;

public class NearbyStations extends Fragment {

    private NearbyStationsModel rateViewModel;
    WebView activity_main_webview;
    View view;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        rateViewModel = ViewModelProviders.of(this).get(NearbyStationsModel.class);
        view = inflater.inflate(R.layout.fragment_share, container, false);

        final TextView textView = view.findViewById(R.id.text_home);


        GPSTracker gps = new GPSTracker(getActivity());
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("URL","http://csafe.msonet.co.in/places.php?lat="+latitude+"&"+"lng="+longitude);
        startActivity(intent);
        rateViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return view;
    }





}
