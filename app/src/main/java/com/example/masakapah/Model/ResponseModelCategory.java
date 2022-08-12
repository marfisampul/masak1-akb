package com.example.masakapah.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseModelCategory {
    @SerializedName("results")

    public List<ModelCategory> results = new ArrayList<>();
    public List<ModelCategory> getResults() { return results; }
}
