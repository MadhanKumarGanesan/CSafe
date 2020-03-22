package com.madcodes.kavalanclone.API;

import com.madcodes.kavalanclone.Models.Requests.LoginRequest;
import com.madcodes.kavalanclone.Models.Requests.RegistrationRequest;
import com.madcodes.kavalanclone.Models.Responses.LoginResponse;
import com.madcodes.kavalanclone.Models.Responses.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("mobilelogin.php")
    Call<LoginResponse>mobilelogin(@Body LoginRequest loginRequest);

    @POST("mobileuserregistration.php")
    Call<RegistrationResponse>mobileuserregistration(@Body RegistrationRequest registrationRequest);

    /*@GET("GetTown")
    Call<TownResponse> GetTown();*/



}
