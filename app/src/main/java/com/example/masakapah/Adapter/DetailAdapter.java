package com.example.masakapah.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masakapah.Model.ModelDetail;
import com.example.masakapah.Model.ModelMasak;
import com.example.masakapah.R;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private List<ModelDetail> items;
    private Context mContext;

    public DetailAdapter(Context context, List<ModelDetail> items) {
        this.mContext = context;
        this.items = items;
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bahan,parent,false);
        return new DetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        final ModelDetail data = items.get(position);

        holder.txtBahan.setText(data.getIngredient());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtBahan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBahan = itemView.findViewById(R.id.txtDetailBahan);
        }
    }
}
