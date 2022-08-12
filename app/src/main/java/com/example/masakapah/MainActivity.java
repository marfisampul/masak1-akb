package com.example.masakapah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.masakapah.Fragment.FragmentCategory;
import com.example.masakapah.Fragment.FragmentFav;
import com.example.masakapah.Fragment.FragmentHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //fragment
        getFragmentPage(new FragmentHome());

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                //Menantukan halaman Fragment yang akan tampil
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = new FragmentHome();
                        break;

                    case R.id.category:
                        fragment = new FragmentCategory();
                        break;

                    case R.id.favorite:
                        fragment = new FragmentFav();
                        break;

                }
                return getFragmentPage(fragment);
            }
        });
    }

    //fragment
    private boolean getFragmentPage(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}