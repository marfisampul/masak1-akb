package com.example.masakapah.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.masakapah.Adapter.MasakAdapter;
import com.example.masakapah.Adapter.SearchAdapter;
import com.example.masakapah.Api.ApiEndPoint;
import com.example.masakapah.Api.ApiService;
import com.example.masakapah.Api.ListApi;
import com.example.masakapah.DetailActivity;
import com.example.masakapah.MainActivity;
import com.example.masakapah.Model.ModelMasak;
import com.example.masakapah.Model.ModelSearch;
import com.example.masakapah.Model.ResponseModel;
import com.example.masakapah.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentHome extends Fragment implements MasakAdapter.onSelectData, SearchAdapter.onSelectData {

    private MasakAdapter masakAdapter;
    private SearchAdapter searchAdapter;
    private List<ModelMasak> mItems = new ArrayList<>();
    private List<ModelSearch> search = new ArrayList<>();

    SearchView searchView;

    @BindView(R.id.recyclerMasak)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);
        //menampilkan masakan
        masakAdapter = new MasakAdapter(getActivity(), mItems, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(masakAdapter);

        searchAdapter = new SearchAdapter(getActivity(), search, this);
        RecyclerView.LayoutManager searchLayout = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(searchLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(searchAdapter);

        searchView = view.findViewById(R.id.cari);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String q) {
                setSearch(q);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals(""))
                    showDataMasakan();
                return false;
            }
        });

        loadDataMasakan();
        return view;

        //search


    }

    private void setSearch(String q) {
        AndroidNetworking.get(ListApi.BASE_URL+ListApi.SEARCH +q)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            search = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelSearch modelSearch = new ModelSearch();

                                modelSearch.setKey(jsonObject.getString("key"));
                                modelSearch.setTitle(jsonObject.getString("title"));
                                modelSearch.setThumb(jsonObject.getString("thumb"));
                                modelSearch.setServing(jsonObject.getString("serving"));
                                modelSearch.setTimes(jsonObject.getString("times"));
                                modelSearch.setDificulty(jsonObject.getString("difficulty"));

                                search.add(modelSearch);
                                showSearchMasakan();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }



    private void loadDataMasakan() {

        AndroidNetworking.get(ListApi.BASE_URL+ListApi.NEW_RESEP)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mItems = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ModelMasak modelMasak = new ModelMasak();

                                modelMasak.setKey(jsonObject.getString("key"));
                                modelMasak.setTitle(jsonObject.getString("title"));
                                modelMasak.setThumb(jsonObject.getString("thumb"));
                                modelMasak.setPortion(jsonObject.getString("portion"));
                                modelMasak.setTimes(jsonObject.getString("times"));
                                modelMasak.setDificulty(jsonObject.getString("dificulty"));

                                mItems.add(modelMasak);
                                showDataMasakan();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
}

    private void showSearchMasakan() {
        searchAdapter = new SearchAdapter(getActivity(), search, this);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }

    private void showDataMasakan() {
        masakAdapter = new MasakAdapter(getActivity(), mItems, this);
        recyclerView.setAdapter(masakAdapter);
        masakAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSelected(ModelMasak masakAdapter) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("detailAktiviti", masakAdapter);
        startActivity(intent);//
    }

    @Override
    public void onSelected(ModelSearch modelSearch) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("detailSearch", modelSearch);
        startActivity(intent);//
    }
}