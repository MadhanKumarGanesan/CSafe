package com.madcodes.kavalanclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.madcodes.kavalanclone.API.ApiClient;
import com.madcodes.kavalanclone.API.ApiInterface;
import com.madcodes.kavalanclone.Models.Requests.LoginRequest;
import com.madcodes.kavalanclone.Models.Responses.LoginResponse;
import com.madcodes.kavalanclone.Utils.CommonClass;
import com.madcodes.kavalanclone.Utils.CustomProgressbar;
import com.madcodes.kavalanclone.Utils.KavalanPreferences;
import com.madcodes.kavalanclone.ui.home.HomeFragment;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView edt_mobilenumber, edt_otp;
    Button btn_login, btn_reg;
    TextView tv_sendOTP;
    ApiInterface apiInterface;
    Callback<LoginResponse> loginResponseCallback;
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    String str_otp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        clickListener();
    }

    private void clickListener() {

        tv_sendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValid()) {
                    if (CommonClass.isNetworkStatusAvialable(getApplicationContext())) {
                        setApicall();
                        getApicall();
                       /* Intent intent = new Intent(LoginActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                        finish();*/

                        // CommonClass.cusomAlertDialog(LoginActivity.this);


                    } else {
                        CommonClass.CreateAlert(LoginActivity.this);
                    }
                }
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateOTP(str_otp)) {
                    KavalanPreferences.setIsLogin(true);
                    Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (CommonClass.checkPermission(LoginActivity.this)) {
                    Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                    startActivity(intent);

                } else {

                    CommonClass.requestPermission(LoginActivity.this);
                }
            }
        });
    }


    private void initViews() {
        edt_mobilenumber = findViewById(R.id.edt_mobilenumber);
        edt_otp = findViewById(R.id.edt_otp);

        btn_login = findViewById(R.id.btn_login);
        btn_reg = findViewById(R.id.btn_reg);

        tv_sendOTP = findViewById(R.id.tv_sendOTP);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }


    private void getApicall() {
        LoginRequest loginRequest = null;
        try {
            loginRequest = new LoginRequest();
            loginRequest.setMobile(edt_mobilenumber.getText().toString());


            System.out.println("mobilelogin---> Request Json: " + gson.toJson(loginRequest));
            CustomProgressbar.showProgressBar(LoginActivity.this, false);

            Call<LoginResponse> getLogin = apiInterface.mobilelogin(loginRequest);
            getLogin.enqueue(loginResponseCallback);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void setApicall() {
        loginResponseCallback = new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (!response.isSuccessful())
                    return;


                LoginResponse loginResponse = response.body();


                String status = loginResponse.getStatus();
                String message = loginResponse.getMessage();
                CustomProgressbar.hideProgressBar();
                System.out.println("rescode" + status);
                System.out.println("mobilelogin---> Response Json: " + gson.toJson(loginResponse));

                if (status.equals("1")) {
                    //Getting Values
                    String otp = loginResponse.getOtp();
                    String userId = loginResponse.getUser_id();

                    //Logics for OTP submit
                    str_otp = otp;
                    edt_mobilenumber.setEnabled(false);

                    //Adding Preferences
                    KavalanPreferences.setUserId(userId);
                    KavalanPreferences.setMsisdn(loginResponse.getUser_details().getUser().getMobile());
                    KavalanPreferences.setUserName(loginResponse.getUser_details().getUser().getUsername());


                } else {
                    CommonClass.cusomAlertDialog(LoginActivity.this, "ATTENTION..!", message);


                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("driverList Failure  :  " + t);

                CustomProgressbar.hideProgressBar();

                t.printStackTrace();

                if (t instanceof SocketTimeoutException) {
                    CommonClass.cusomAlertDialog(LoginActivity.this, getString(R.string.attention), getString(R.string.service_temp_unavail));
                } else if (t instanceof UnknownHostException) {
                    CommonClass.cusomAlertDialog(LoginActivity.this, getString(R.string.attention), getString(R.string.service_temp_unavail));
                } else if (t instanceof ConnectException) {
                    CommonClass.cusomAlertDialog(LoginActivity.this, getString(R.string.oops), getString(R.string.no_connection));
                } else if (t instanceof Exception) {
                    CommonClass.cusomAlertDialog(LoginActivity.this, getString(R.string.attention), getString(R.string.no_connection));
                }

            }
        };


    }

    private boolean validateOTP(String Otp) {


        if (edt_otp.getText().toString().length() == 0) {
            CommonClass.cusomAlertDialog(LoginActivity.this, getString(R.string.attention), "Enter OTP");

            return false;
        } else if (!edt_otp.getText().toString().equals(Otp)) {
            CommonClass.cusomAlertDialog(LoginActivity.this, getString(R.string.attention), "Invalid OTP");

            return false;
        }
        return true;

    }

    private boolean isValid() {

        if (edt_mobilenumber.getText().toString().length() == 0) {
            CommonClass.cusomAlertDialog(LoginActivity.this, getString(R.string.attention), "Enter Mobile number.");
            return false;
        }

        return true;

    }
}