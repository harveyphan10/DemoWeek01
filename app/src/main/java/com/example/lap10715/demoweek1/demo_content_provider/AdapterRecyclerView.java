package com.example.lap10715.demoweek1.demo_content_provider;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lap10715.demoweek1.R;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    private ArrayList<ListPhotoSameDate> data;
    private Context context;

    public AdapterRecyclerView(Context context, ArrayList<ListPhotoSameDate> data) {
        this.data = data;
        this.context = context;
    }

    public void setData(ArrayList<ListPhotoSameDate> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListPhotoSameDate curLstPhoto = data.get(position);
        holder.tvDate.setText(curLstPhoto.getDate());

        holder.rvItem.setHasFixedSize(true);

        //if(holder.adapterInner == null){
        holder.rvItem.setAdapter(new AdapterInnerRecyclerView(context,
                curLstPhoto.getLstPhotoHaveSameDate()));
        holder.rvItem.setLayoutManager(new GridLayoutManager(context, 4));
        // }

        //holder.rvItem.setAdapter(holder.adapterInner);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDate;
        private RecyclerView rvItem;
        private AdapterInnerRecyclerView adapterInner;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tv_date);
            rvItem = itemView.findViewById(R.id.rv_item);

        }

    }
}