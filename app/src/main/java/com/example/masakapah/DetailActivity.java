package com.example.masakapah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.masakapah.Adapter.DetailAdapter;
import com.example.masakapah.Api.ListApi;
import com.example.masakapah.Model.ModelDetail;
import com.example.masakapah.Model.ModelMasak;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView txtJudul, txtKesulitan, txtPorsi, txtWaktu;
    ImageView imgThumb;
    RecyclerView bahan, langkah;
    ModelMasak modelMasak;
    DetailAdapter detailAdapter;
    List<ModelDetail> modelDetails = new ArrayList<>();
    String title, dificulty, portion, times, thumb, key, bahanMasakan;
    ProgressDialog progressDialog;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbarDetail);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang Menampilkan Masakan");

        imgThumb = findViewById(R.id.imgThumb);
        txtJudul = findViewById(R.id.txtJudul);
        txtKesulitan = findViewById(R.id.txtKesulitan);
        txtPorsi = findViewById(R.id.txtPorsi);
        txtWaktu = findViewById(R.id.txtWaktu);
        bahan = findViewById(R.id.recyclerBahan);

        modelMasak = (ModelMasak) getIntent().getSerializableExtra("detailAktiviti");
        if(modelMasak != null){
            title = modelMasak.getTitle();
            thumb = modelMasak.getThumb();
            times = modelMasak.getTimes();
            portion = modelMasak.getPortion();
            dificulty = modelMasak.getDificulty();

            txtJudul.setText(title);
            txtKesulitan.setText(dificulty);
            txtPorsi.setText(portion);
            txtWaktu.setText(times);

            Glide.with(this)
                    .load(thumb)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgThumb);

            bahan.setHasFixedSize(true);
            bahan.setLayoutManager(new LinearLayoutManager(this));
            getBahan();
        }
    }

    private void getBahan() {
        AndroidNetworking.get(ListApi.BASE_URL + ListApi.DETAIL_RESEP)
                .addPathParameter("key",String.valueOf(key))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelDetail data = new ModelDetail();
                                data.setIngredient(jsonObject.getString("ingredient"));
                                modelDetails.add(data);
                                showTrailer();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void showTrailer() {
        detailAdapter = new DetailAdapter(DetailActivity.this, modelDetails);
        bahan.setAdapter(detailAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}