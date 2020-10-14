package com.kishor_bhattarai.uri;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url
{
    public static final String BASE_URL = "http://10.0.2.2:3000/";
    //public static final String BASE_URL = "http://172.26.0.184:3000/";

    public static String userid="";
    public static String Cookie;

    public static Retrofit getInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
