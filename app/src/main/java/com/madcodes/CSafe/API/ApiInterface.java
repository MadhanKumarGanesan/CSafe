package com.madcodes.CSafe.API;

import com.madcodes.CSafe.Models.Requests.LoginRequest;
import com.madcodes.CSafe.Models.Requests.RegistrationRequest;
import com.madcodes.CSafe.Models.Requests.SOSRequest;
import com.madcodes.CSafe.Models.Responses.LoginResponse;
import com.madcodes.CSafe.Models.Responses.RegistrationResponse;
import com.madcodes.CSafe.Models.Responses.SOSResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("mobilelogin.php")
    Call<LoginResponse>mobilelogin(@Body LoginRequest loginRequest);

    @POST("mobileuserregistration.php")
    Call<RegistrationResponse>mobileuserregistration(@Body RegistrationRequest registrationRequest);

    @POST("mobilesos.php")
    Call<SOSResponse>mobilesos(@Body SOSRequest sosRequest);

    /*@GET("GetTown")
    Call<TownResponse> GetTown();*/


}
