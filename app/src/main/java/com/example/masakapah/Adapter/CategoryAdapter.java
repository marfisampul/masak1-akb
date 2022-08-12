package com.example.masakapah.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.masakapah.Model.ModelCategory;
import com.example.masakapah.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

    List<ModelCategory> wList;
    Context ctxt;

    public CategoryAdapter(Context ctxt, List<ModelCategory> mList){
        this.wList = mList;
        this.ctxt = ctxt;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_category,parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyHolder holder, int position) {
        ModelCategory modelCategory = wList.get(position);
        holder.category.setText(modelCategory.getCategory());
    }

    @Override
    public int getItemCount() {
        return wList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView category;
        public MyHolder(@NonNull View itemView){
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.txtCategories);
        }
    }
}
