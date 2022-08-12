package com.example.masakapah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.masakapah.Adapter.MasakAdapter;
import com.example.masakapah.Api.ApiEndPoint;
import com.example.masakapah.Api.ApiService;
import com.example.masakapah.Fragment.FragmentCategory;
import com.example.masakapah.Fragment.FragmentFav;
import com.example.masakapah.Fragment.FragmentHome;
import com.example.masakapah.Fragment.FragmentSetting;
import com.example.masakapah.Model.ModelMasak;
import com.example.masakapah.Model.ResponseModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                    case R.id.setting:
                        fragment = new FragmentSetting();
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