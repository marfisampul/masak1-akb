package com.example.masakapah.Api;

import com.example.masakapah.Model.ResponseModelCategory;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceCategory {

    @GET("api/category/recipes")
    Call<ResponseModelCategory> getKatgori();
}
