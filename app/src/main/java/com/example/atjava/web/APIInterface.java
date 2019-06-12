package com.example.atjava.web;

import com.example.atjava.models.Constants;
import com.example.atjava.models.Json;
import retrofit2.Call;
import retrofit2.http.GET;



public interface APIInterface {

    @GET(Constants.GET_INFO)
    Call<Json> getInfo();

}