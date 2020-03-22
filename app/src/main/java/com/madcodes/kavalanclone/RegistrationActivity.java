package com.madcodes.kavalanclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.madcodes.kavalanclone.API.ApiClient;
import com.madcodes.kavalanclone.API.ApiInterface;
import com.madcodes.kavalanclone.Models.Requests.LoginRequest;
import com.madcodes.kavalanclone.Models.Requests.RegistrationRequest;
import com.madcodes.kavalanclone.Models.Responses.LoginResponse;
import com.madcodes.kavalanclone.Models.Responses.RegistrationResponse;
import com.madcodes.kavalanclone.Utils.CommonClass;
import com.madcodes.kavalanclone.Utils.CustomProgressbar;
import com.madcodes.kavalanclone.Utils.GPSTracker;
import com.madcodes.kavalanclone.Utils.KavalanPreferences;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText edt_email, edt_fName, edt_Lname, edt_addr1, edt_addr2, edt_city, edt_acc_mobile,
            edt_person1_name, edt_person1_mobile, edt_person2_name, edt_person2_mobile, edt_person3_name, edt_person3_mobile;
    TextView tv_dob;
    RadioGroup Gender_group;
    RadioButton rb_male, rb_female;

    Button btn_signup;

    ApiInterface apiInterface;
    Callback<RegistrationResponse> registrationResponseCallback;
    Callback<LoginResponse> loginResponseCallback;
    String str_otp = "";
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
        setCurrentLocation();
        clickListeners();
    }

    private void clickListeners() {

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIsvalid()) {
                    if (CommonClass.isNetworkStatusAvialable(getApplicationContext())) {
                        setApicall();
                        getApicall();


                    } else {
                        CommonClass.CreateAlert(RegistrationActivity.this);
                    }
                }
            }
        });
        tv_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCalendar();
                DatePickerDialog datePickerDialog = new DatePickerDialog
                        (RegistrationActivity.this, date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH));

                //following line to restrict future date selection
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

    }
    private void setCalendar() {
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
    }
    private void updateLabel() {

        try {
            String myFormat = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            tv_dob.setText(sdf.format(myCalendar.getTime()));
        }catch (Exception e){
            Toast.makeText(this, "Date not supported ", Toast.LENGTH_SHORT).show();
        }


        /*try{
            String myFormat = "dd/MM/YYYY";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            edt_dob.setText(sdf.format(myCalendar.getTime()));
        }catch (Exception e){
            Toast.makeText(this, "Date not supported ", Toast.LENGTH_SHORT).show();

        }*/



    }


    private void setCurrentLocation() {
        GPSTracker gps = new GPSTracker(this);
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String cityName = addresses.get(0).getAddressLine(0);

            String[] spliting = cityName.split(",");
            String country = spliting[spliting.length - 1].replace(" ", "");
            String state = spliting[spliting.length - 2];
            String city = spliting[spliting.length - 3].replace(" ", "");

            edt_city.setText(city + ", " + state + ", " + country);
            edt_addr1.setText(cityName);
            edt_addr2.setText(cityName);
            Log.e("CityName", city);
            Log.e("stateName", state);
            Log.e("CountryName", country);

            Log.e("test", addresses.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initViews() {

        edt_email = findViewById(R.id.edt_email);
        edt_fName = findViewById(R.id.edt_fName);
        edt_Lname = findViewById(R.id.edt_Lname);
        edt_addr1 = findViewById(R.id.edt_addr1);
        edt_addr2 = findViewById(R.id.edt_addr2);
        edt_city = findViewById(R.id.edt_city);
        edt_acc_mobile = findViewById(R.id.edt_acc_mobile);
        edt_person1_name = findViewById(R.id.edt_person1_name);
        edt_person1_mobile = findViewById(R.id.edt_person1_mobile);
        edt_person2_name = findViewById(R.id.edt_person2_name);
        edt_person2_mobile = findViewById(R.id.edt_person2_mobile);
        edt_person3_name = findViewById(R.id.edt_person3_name);
        edt_person3_mobile = findViewById(R.id.edt_person3_mobile);

        tv_dob = findViewById(R.id.tv_dob);
        myCalendar = Calendar.getInstance();

        Gender_group = findViewById(R.id.rg_Gender);

        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);


        btn_signup = findViewById(R.id.btn_signup);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    private boolean checkIsvalid() {
        if (edt_email.getText().toString().isEmpty()) {
            edt_email.requestFocus();
            Toast.makeText(this, "Please Enter Email.", Toast.LENGTH_SHORT).show();
            return false;

        } else if (!edt_email.getText().toString().isEmpty() && !CommonClass.validateEmail(edt_email.getText().toString())) {
            Toast.makeText(this, "Please Enter valid Email.", Toast.LENGTH_SHORT).show();

            return false;

        } else if (edt_fName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter First Name.", Toast.LENGTH_SHORT).show();

            return false;

        } else if (edt_Lname.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Last Name.", Toast.LENGTH_SHORT).show();

            return false;

        } else if (Gender_group.getCheckedRadioButtonId() == -1) {

            return false;

        } else if (tv_dob.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Date of Birth.", Toast.LENGTH_SHORT).show();

            return false;
        } else if (edt_addr1.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Address 1.", Toast.LENGTH_SHORT).show();

            return false;
        } else if (edt_city.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter City.", Toast.LENGTH_SHORT).show();

            return false;
        } else if (edt_acc_mobile.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Mobile Number.", Toast.LENGTH_SHORT).show();

            return false;
        } else if (edt_person1_name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Person 1 Name.", Toast.LENGTH_SHORT).show();

            return false;
        } else if (edt_person1_mobile.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Person 1 Mobile.", Toast.LENGTH_SHORT).show();

            return false;
        } else if (edt_person2_name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Person 2 Name.", Toast.LENGTH_SHORT).show();

            return false;
        } else if (edt_person2_mobile.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Person 2 Mobile.", Toast.LENGTH_SHORT).show();

            return false;
        } else if (edt_person3_name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Person 3 Name.", Toast.LENGTH_SHORT).show();

            return false;
        } else if (edt_person3_mobile.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter Person 3 Mobile.", Toast.LENGTH_SHORT).show();

            return false;
        }
        return true;
    }


    private void getApicall() {
        RegistrationRequest loginRequest = null;
        try {

            GPSTracker gps = new GPSTracker(this);
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();


            loginRequest = new RegistrationRequest();
            loginRequest.setUsername(edt_fName.getText().toString() + " " + edt_Lname.getText().toString());
            loginRequest.setMobile(edt_acc_mobile.getText().toString());
            loginRequest.setEmail(edt_email.getText().toString());
            loginRequest.setLat(String.valueOf(latitude));
            loginRequest.setLng(String.valueOf(longitude));
            loginRequest.setAge(tv_dob.getText().toString());
            loginRequest.setGender(rb_male.isChecked() ? "Male" : rb_female.isChecked() ? "Female" : "Unknown");
            loginRequest.setAddress1(edt_addr1.getText().toString());
            loginRequest.setAddress2(edt_addr2.getText().toString());
            loginRequest.setCity(edt_city.getText().toString());
            loginRequest.setRelation1(edt_person1_name.getText().toString());
            loginRequest.setMobile1(edt_person1_mobile.getText().toString());
            loginRequest.setRelation2(edt_person2_name.getText().toString());
            loginRequest.setMobile2(edt_person2_mobile.getText().toString());
            loginRequest.setRelation3(edt_person3_name.getText().toString());
            loginRequest.setMobile3(edt_person3_mobile.getText().toString());

            System.out.println("mobilelogin---> Request Json: " + gson.toJson(loginRequest));
            CustomProgressbar.showProgressBar(RegistrationActivity.this, false);

            Call<RegistrationResponse> getLogin = apiInterface.mobileuserregistration(loginRequest);
            getLogin.enqueue(registrationResponseCallback);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void setApicall() {
        registrationResponseCallback = new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {

                if (!response.isSuccessful())
                    return;


                RegistrationResponse registrationResponse = response.body();


                String status = registrationResponse.getStatus();
                String message = registrationResponse.getMessage();
                CustomProgressbar.hideProgressBar();
                System.out.println("rescode" + status);
                System.out.println("mobilelogin---> Response Json: " + gson.toJson(registrationResponse));

                if (status.equals("1")) {
                    //Getting Values
                    setLoginApicall();
                    getLoginApicall(registrationResponse.getUser_details().getUser().getMobile());


                } else {
                    CommonClass.cusomAlertDialog(RegistrationActivity.this, "ATTENTION..!", message);


                }

            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                System.out.println("driverList Failure  :  " + t);

                CustomProgressbar.hideProgressBar();

                t.printStackTrace();

                if (t instanceof SocketTimeoutException) {
                    CommonClass.cusomAlertDialog(RegistrationActivity.this, getString(R.string.attention), getString(R.string.service_temp_unavail));
                } else if (t instanceof UnknownHostException) {
                    CommonClass.cusomAlertDialog(RegistrationActivity.this, getString(R.string.attention), getString(R.string.service_temp_unavail));
                } else if (t instanceof ConnectException) {
                    CommonClass.cusomAlertDialog(RegistrationActivity.this, getString(R.string.oops), getString(R.string.no_connection));
                } else if (t instanceof Exception) {
                    CommonClass.cusomAlertDialog(RegistrationActivity.this, getString(R.string.attention), getString(R.string.no_connection));
                }

            }
        };


    }


    private void getLoginApicall(String mobile) {
        LoginRequest loginRequest = null;
        try {
            loginRequest = new LoginRequest();
            loginRequest.setMobile(mobile);


            System.out.println("mobilelogin---> Request Json: " + gson.toJson(loginRequest));
            CustomProgressbar.showProgressBar(RegistrationActivity.this, false);

            Call<LoginResponse> getLogin = apiInterface.mobilelogin(loginRequest);
            getLogin.enqueue(loginResponseCallback);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void setLoginApicall() {
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
                    // edt_mobilenumber.setEnabled(false);

                    //Adding Preferences
                    KavalanPreferences.setUserId(userId);
                    KavalanPreferences.setMsisdn(loginResponse.getUser_details().getUser().getMobile());
                    KavalanPreferences.setUserName(loginResponse.getUser_details().getUser().getUsername());

                    Intent intent = new Intent(RegistrationActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    CommonClass.cusomAlertDialog(RegistrationActivity.this, "ATTENTION..!", message);


                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("driverList Failure  :  " + t);

                CustomProgressbar.hideProgressBar();

                t.printStackTrace();

                if (t instanceof SocketTimeoutException) {
                    CommonClass.cusomAlertDialog(RegistrationActivity.this, getString(R.string.attention), getString(R.string.service_temp_unavail));
                } else if (t instanceof UnknownHostException) {
                    CommonClass.cusomAlertDialog(RegistrationActivity.this, getString(R.string.attention), getString(R.string.service_temp_unavail));
                } else if (t instanceof ConnectException) {
                    CommonClass.cusomAlertDialog(RegistrationActivity.this, getString(R.string.oops), getString(R.string.no_connection));
                } else if (t instanceof Exception) {
                    CommonClass.cusomAlertDialog(RegistrationActivity.this, getString(R.string.attention), getString(R.string.no_connection));
                }

            }
        };


    }
}
