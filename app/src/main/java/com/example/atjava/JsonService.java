package com.example.atjava;

import com.example.atjava.models.RetrofitJson;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonService {

    public static final String BASE_URL = "https://demo1112976.mockable.io/";
    @GET("infnet")

    Call<RetrofitJson> listJson();
}
