package com.example.masakapah.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.masakapah.Api.ApiEndPoint;
import com.example.masakapah.Model.ModelMasak;
import com.example.masakapah.R;

import java.util.List;

public class MasakAdapter extends RecyclerView.Adapter<MasakAdapter.MyHolder>{
    List<ModelMasak> mList ;
    Context ctx ;
    MasakAdapter.onSelectData onSelectData;

    public interface onSelectData {
        void onSelected(ModelMasak masakAdapter);
    }

    public MasakAdapter(Context ctx, List<ModelMasak> mList, MasakAdapter.onSelectData onSelectData1) {
        this.mList = mList;
        this.ctx = ctx;
        this.onSelectData = onSelectData1;
    }


    @NonNull
    @Override
    public MasakAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MasakAdapter.MyHolder holder, int position) {
        ModelMasak modelMasak = mList.get(position);
        Glide.with(ctx)
                .load(modelMasak.thumb)
                .centerCrop()
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                .transform(new RoundedCorners(22)))
                .into(holder.thumb);
        holder.title.setText(modelMasak.getTitle());
        holder.cardViewMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectData.onSelected(modelMasak);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView  title;
        ImageView thumb;
        CardView cardViewMovie;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            title  = (TextView) itemView.findViewById(R.id.txt1);
            thumb = (ImageView) itemView.findViewById(R.id.img1);
            cardViewMovie =itemView.findViewById(R.id.itemView);
        }
    }
}
