package com.madcodes.CSafe.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.madcodes.CSafe.API.ApiClient;
import com.madcodes.CSafe.API.ApiInterface;
import com.madcodes.CSafe.Models.RelationList;
import com.madcodes.CSafe.Models.Requests.RelationVerifyRequest;
import com.madcodes.CSafe.Models.Requests.SOSRequest;
import com.madcodes.CSafe.Models.Responses.RelationVerifyResponse;
import com.madcodes.CSafe.Models.Responses.SOSResponse;
import com.madcodes.CSafe.R;
import com.madcodes.CSafe.Utils.CSafePreferences;
import com.madcodes.CSafe.Utils.CommonClass;
import com.madcodes.CSafe.Utils.CustomProgressbar;
import com.madcodes.CSafe.Utils.GPSTracker;


import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrepaidBundlesAdapter extends RecyclerView.Adapter<PrepaidBundlesAdapter.MyViewHolder> {


    private ArrayList<RelationList> mbundlelist;
    private Activity activity;
    Callback<RelationVerifyResponse> relationVerifyResponseCallback;
    ApiInterface apiInterface;
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();


    public PrepaidBundlesAdapter(Activity activity, ArrayList<RelationList> bundlelist) {
        this.activity = activity;
        this.mbundlelist = bundlelist;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pre_pay_packs_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.tv_relation_name.setText("Name :"+mbundlelist.get(position).getRelation_name());
        holder.tv_relation_mobile.setText("Mobile :"+mbundlelist.get(position).getRelation_mobile());
        holder.btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonClass.checkPermission(activity)) {

                    if(!holder.edt_otp.getText().toString().isEmpty()){
                        setApicall();
                        getApicall(mbundlelist,position,holder.edt_otp.getText().toString());
                    }
                    else{
                        CommonClass.cusomAlertDialog(activity, "ATTENTION..!", "Please enter OTP.");
                    }

                } else {

                    CommonClass.requestPermission(activity);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mbundlelist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_relation_mobile, tv_relation_name;
        EditText edt_otp;
        Button btn_verify;

        private MyViewHolder(View view) {
            super(view);

             edt_otp = view.findViewById(R.id.edt_otp);
             tv_relation_mobile = view.findViewById(R.id.tv_relation_mobile);
             tv_relation_name = view.findViewById(R.id.tv_relation_name);
             btn_verify = view.findViewById(R.id.btn_verify);


        }
    }


    private void getApicall(ArrayList<RelationList> mbundlelist,int position, String otp) {
        RelationVerifyRequest loginRequest = null;
        try {



            loginRequest = new RelationVerifyRequest();
            loginRequest.setMobile(CSafePreferences.getMsisdn());
            loginRequest.setOtp(otp);
            loginRequest.setRelation_id(""+(position+1));
            loginRequest.setRelation_mobile(mbundlelist.get(position).getRelation_mobile());


            System.out.println("mobilerelationverify---> Request Json: " + gson.toJson(loginRequest));
            CustomProgressbar.showProgressBar(activity, false);

            Call<RelationVerifyResponse> getLogin = apiInterface.mobilerelationverify(loginRequest);
            getLogin.enqueue(relationVerifyResponseCallback);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void setApicall() {
        relationVerifyResponseCallback = new Callback<RelationVerifyResponse>() {
            @Override
            public void onResponse(Call<RelationVerifyResponse> call, Response<RelationVerifyResponse> response) {

                if (!response.isSuccessful())
                    return;


                RelationVerifyResponse loginResponse = response.body();


                String status = loginResponse.getStatus();
                String message = loginResponse.getMessage();
                CustomProgressbar.hideProgressBar();
                System.out.println("rescode" + status);
                System.out.println("mobilerelationverify---> Response Json: " + gson.toJson(loginResponse));

                if (status.equals("1")) {
                    //Getting Values
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

                } else {
                    CommonClass.cusomAlertDialog(activity, "ATTENTION..!", message);


                }

            }

            @Override
            public void onFailure(Call<RelationVerifyResponse> call, Throwable t) {
                System.out.println("driverList Failure  :  " + t);

                CustomProgressbar.hideProgressBar();

                t.printStackTrace();

                if (t instanceof SocketTimeoutException) {
                    CommonClass.cusomAlertDialog(activity, activity.getString(R.string.attention), activity.getString(R.string.service_temp_unavail));
                } else if (t instanceof UnknownHostException) {
                    CommonClass.cusomAlertDialog(activity, activity.getString(R.string.attention), activity.getString(R.string.service_temp_unavail));
                } else if (t instanceof ConnectException) {
                    CommonClass.cusomAlertDialog(activity, activity.getString(R.string.oops), activity.getString(R.string.no_connection));
                } else if (t instanceof Exception) {
                    CommonClass.cusomAlertDialog(activity, activity.getString(R.string.attention), activity.getString(R.string.no_connection));
                }

            }
        };


    }
}
