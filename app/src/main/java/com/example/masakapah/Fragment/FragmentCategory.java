package com.example.masakapah.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.masakapah.Adapter.CategoryAdapter;
import com.example.masakapah.Api.ApiEndPoint;
import com.example.masakapah.Api.ApiServiceCategory;
import com.example.masakapah.Model.ModelCategory;
import com.example.masakapah.Model.ResponseModelCategory;
import com.example.masakapah.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentCategory extends Fragment {

    private CategoryAdapter categoryAdapter;
    private List<ModelCategory> mItems = new ArrayList<>();

    @BindView(R.id.recyclerkategori)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        ButterKnife.bind(this, view);
        //menampilkan data
        categoryAdapter = new CategoryAdapter(getActivity(), mItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(categoryAdapter);

        loadDataKategori();
        return view;
    }

    private void loadDataKategori() {

        ApiServiceCategory apiapi = ApiEndPoint.getClient().create(ApiServiceCategory.class);

        Call<ResponseModelCategory> call = apiapi.getKatgori();
        call.enqueue(new Callback<ResponseModelCategory>() {
            @Override
            public void onResponse(Call<ResponseModelCategory> call, Response<ResponseModelCategory> response) {
                mItems = response.body().results;
                categoryAdapter = new CategoryAdapter(getActivity(), mItems);
                recyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<ResponseModelCategory> call, Throwable t) {

            }
        });
    }

}