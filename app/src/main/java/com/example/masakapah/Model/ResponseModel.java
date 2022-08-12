package com.example.masakapah.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseModel {
    @SerializedName("results")
    public List<ModelMasak> results = new ArrayList<>();
    public List<ModelMasak> getResults(){
        return results;
    }
}
