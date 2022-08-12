package com.example.masakapah.Model;

import java.io.Serializable;

public class ModelSearch implements Serializable {
    public String title;
    public String thumb;
    public String key;
    public String times;
    public String serving;
    public String difficulty;

    public String getTitle(){ return title; }
    public void setTitle(String Title) {title = Title;}

    public String getThumb(){ return thumb; }
    public void setThumb(String Thumb) {thumb = Thumb;}

    public String getKey(){ return key; }
    public void setKey(String Key) {key = Key;}

    public String getTimes(){ return times; }
    public void setTimes(String Times) {times = Times;}

    public String getServing(){ return serving; }
    public void setServing(String Serving) {serving = serving;}

    public String getDificulty(){ return difficulty; }
    public void setDificulty(String Dificulty) {difficulty = Dificulty;}
}
