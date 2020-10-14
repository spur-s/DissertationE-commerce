package com.kishor_bhattarai.easymanpower.Interfaces;

import com.kishor_bhattarai.easymanpower.models.ImageResponse;
import com.kishor_bhattarai.easymanpower.models.Manpower;
import com.kishor_bhattarai.easymanpower.models.Review;
import com.kishor_bhattarai.easymanpower.models.ReviewResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ManpowerInterface {


    @POST("items")
    Call<Void> addManpower(@Body Manpower event);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("/items")
    Call<List<Manpower>> getAllManpower();

    @POST("reviews")
    Call<ReviewResponse> postReview (@Body Review review);

    @GET("/reviews")
    Call<List<Review>> getAllReview() ;

}
