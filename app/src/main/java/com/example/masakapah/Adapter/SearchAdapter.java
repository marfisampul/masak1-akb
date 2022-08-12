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
import com.example.masakapah.Model.ModelMasak;
import com.example.masakapah.Model.ModelSearch;
import com.example.masakapah.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    List<ModelSearch> qList ;
    Context ctx ;
    SearchAdapter.onSelectData onSelectData;

    public interface onSelectData {
        void onSelected(ModelSearch modelSearch);
    }

    public SearchAdapter(Context ctx, List<ModelSearch> qList, SearchAdapter.onSelectData onSelectData1) {
        this.qList = qList;
        this.ctx = ctx;
        this.onSelectData = onSelectData1;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        ViewHolder viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelSearch modelSearch = qList.get(position);
        Glide.with(ctx)
                .load(modelSearch.thumb)
                .centerCrop()
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                        .transform(new RoundedCorners(22)))
                .into(holder.thumb);
        holder.title.setText(modelSearch.getTitle());
        holder.cardViewMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectData.onSelected(modelSearch);
            }
        });
    }

    @Override
    public int getItemCount() {
        return qList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView thumb;
        CardView cardViewMovie;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title  = (TextView) itemView.findViewById(R.id.txt1);
            thumb = (ImageView) itemView.findViewById(R.id.img1);
            cardViewMovie =itemView.findViewById(R.id.itemView);
        }
    }
}
