package com.example.masakapah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //inisiasi handler
        Handler handler = new Handler();

        //mulai handler
        handler.postDelayed(() ->{
            //inialisiasi intent
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            //stop intent
            finish();

        }, 3000);
    }
}