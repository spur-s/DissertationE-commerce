package com.kishor_bhattarai.easymanpower.Interfaces;

import com.kishor_bhattarai.easymanpower.models.LoginSignupResponse;
import com.kishor_bhattarai.easymanpower.models.RegisterResponse;
import com.kishor_bhattarai.easymanpower.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserInterface {
    @POST("users/signup")
    Call<RegisterResponse> registerUser(
            @Body User user
    );
    @FormUrlEncoded
    @POST("users/login")
    Call<LoginSignupResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @GET("users/{id}")
    Call<User> getUserProfile(@Path("id") String id);

}
