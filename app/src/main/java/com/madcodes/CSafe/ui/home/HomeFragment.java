package com.madcodes.CSafe.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.madcodes.CSafe.API.ApiClient;
import com.madcodes.CSafe.API.ApiInterface;
import com.madcodes.CSafe.Models.Requests.SOSRequest;
import com.madcodes.CSafe.Models.Responses.SOSResponse;
import com.madcodes.CSafe.R;
import com.madcodes.CSafe.Utils.CommonClass;
import com.madcodes.CSafe.Utils.CustomProgressbar;
import com.madcodes.CSafe.Utils.GPSTracker;
import com.madcodes.CSafe.Utils.CSafePreferences;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button btn_sos;
    ApiInterface apiInterface;
    Callback<SOSResponse> sosResponseCallback;
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(root);
        onclickListeners();
        return root;
    }

    private void initViews(View root) {
        btn_sos = root.findViewById(R.id.btn_sos);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    private void onclickListeners() {
        btn_sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonClass.checkPermission(getActivity())) {
                    setApicall();
                    getApicall();
                } else {

                    CommonClass.requestPermission(getActivity());
                }
            }
        });
    }

    private void getApicall() {
        SOSRequest loginRequest = null;
        try {

            GPSTracker gps = new GPSTracker(getActivity());
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();


            loginRequest = new SOSRequest();
            loginRequest.setMobile(CSafePreferences.getMsisdn());
            loginRequest.setLat("" + latitude);
            loginRequest.setLng("" + longitude);


            System.out.println("mobilesos---> Request Json: " + gson.toJson(loginRequest));
            CustomProgressbar.showProgressBar(getActivity(), false);

            Call<SOSResponse> getLogin = apiInterface.mobilesos(loginRequest);
            getLogin.enqueue(sosResponseCallback);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void setApicall() {
        sosResponseCallback = new Callback<SOSResponse>() {
            @Override
            public void onResponse(Call<SOSResponse> call, Response<SOSResponse> response) {

                if (!response.isSuccessful())
                    return;


                SOSResponse loginResponse = response.body();


                String status = loginResponse.getStatus();
                String message = loginResponse.getMessage();
                CustomProgressbar.hideProgressBar();
                System.out.println("rescode" + status);
                System.out.println("mobilesos---> Response Json: " + gson.toJson(loginResponse));

                if (status.equals("1")) {
                    //Getting Values
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                } else {
                    CommonClass.cusomAlertDialog(getActivity(), "ATTENTION..!", message);


                }

            }

            @Override
            public void onFailure(Call<SOSResponse> call, Throwable t) {
                System.out.println("driverList Failure  :  " + t);

                CustomProgressbar.hideProgressBar();

                t.printStackTrace();

                if (t instanceof SocketTimeoutException) {
                    CommonClass.cusomAlertDialog(getActivity(), getString(R.string.attention), getString(R.string.service_temp_unavail));
                } else if (t instanceof UnknownHostException) {
                    CommonClass.cusomAlertDialog(getActivity(), getString(R.string.attention), getString(R.string.service_temp_unavail));
                } else if (t instanceof ConnectException) {
                    CommonClass.cusomAlertDialog(getActivity(), getString(R.string.oops), getString(R.string.no_connection));
                } else if (t instanceof Exception) {
                    CommonClass.cusomAlertDialog(getActivity(), getString(R.string.attention), getString(R.string.no_connection));
                }

            }
        };


    }
}
