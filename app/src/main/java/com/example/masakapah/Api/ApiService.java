package com.example.masakapah.Api;

import com.example.masakapah.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/recipes")
    Call<ResponseModel> getMasakan();

}
