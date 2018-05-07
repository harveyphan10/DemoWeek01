package com.example.lap10715.demoweek1.demo_content_provider;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.lap10715.demoweek1.GlideApp;
import com.example.lap10715.demoweek1.R;

import java.util.ArrayList;

public class AdapterInnerRecyclerView
        extends RecyclerView.Adapter<AdapterInnerRecyclerView.InnerViewHolder>{


    private ArrayList<Photo> data;
    private Context context;

    public AdapterInnerRecyclerView(Context context, ArrayList<Photo> data) {
        this.data = data;
        this.context = context;
    }

    public void setData(ArrayList<Photo> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterInnerRecyclerView.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_img_item,
                parent, false);
        return new InnerViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        final Photo curPhoto = data.get(position);


        GlideApp.with(context).load(curPhoto.getUrl())
                .placeholder(R.drawable.place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(holder.ivItem);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class InnerViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivItem;

        public InnerViewHolder(View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.iv_item);

        }

    }
}