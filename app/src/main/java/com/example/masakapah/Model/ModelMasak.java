package com.example.masakapah.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelMasak implements Serializable {

    public String title;

    public String thumb;

    public String key;

    public String times;

    public String portion;

    public String dificulty;

    public String Ingredient;

    public String getTitle(){ return title; }
    public void setTitle(String Title) {title = Title;}

    public String getThumb(){ return thumb; }
    public void setThumb(String Thumb) {thumb = Thumb;}

    public String getKey(){ return key; }
    public void setKey(String Key) {key = Key;}

    public String getTimes(){ return times; }
    public void setTimes(String Times) {times = Times;}

    public String getPortion(){ return portion; }
    public void setPortion(String Portion) {portion = Portion;}

    public String getDificulty(){ return dificulty; }
    public void setDificulty(String Dificulty) {dificulty = Dificulty;}

    public String getIngredient(){return Ingredient;}
    public void setIngredient(String ingredient){Ingredient= ingredient;}
}
