package com.example.masakapah.Model;

import com.google.gson.annotations.SerializedName;

public class ModelCategory {

    @SerializedName("category")
    public String category;

    @SerializedName("url")
    public String url;

    @SerializedName("key")
    public String key;

    public String getCategory(){ return category; }
    public String getUrl(){ return url; }
    public String getKey(){ return key; }

}
