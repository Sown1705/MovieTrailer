package com.vincenzocassown.moviestrailer.adapter;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vincenzocassown.moviestrailer.DetailActivity;
import com.vincenzocassown.moviestrailer.databinding.ItemBinding;
import com.vincenzocassown.moviestrailer.model.popular.Result;

import java.util.ArrayList;
import java.util.List;

public class MoviePopularAdapter extends RecyclerView.Adapter<MoviePopularAdapter.ViewHolder> {
    private List<Result> list = new ArrayList<>();
    public void setList(List<Result> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
         ItemBinding binding =ItemBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.binding.getRoot())
                .load("https://image.tmdb.org/t/p/w500"+list.get(position).getPosterPath())
                .into(holder.binding.imageView);
        holder.binding.itemPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick(holder, position);
            }
        });
        holder.binding.setPopular(list.get(position));
        holder.binding.executePendingBindings();
    }

    private void itemClick(ViewHolder holder, int position) {
        Intent i = new Intent(holder.binding.getRoot().getContext(), DetailActivity.class);
//        Bundle bundle = new Bundle();
        i.putExtra("id",list.get(position).getId());
//        bundle.putSerializable("popular",list.get(position));
//        i.putExtras(bundle);
        holder.binding.getRoot().getContext().startActivity(i);
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemBinding binding;
        public ViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
