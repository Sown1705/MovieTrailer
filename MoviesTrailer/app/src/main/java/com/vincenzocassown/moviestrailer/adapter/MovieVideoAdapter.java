package com.vincenzocassown.moviestrailer.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vincenzocassown.moviestrailer.PlayerActivity;
import com.vincenzocassown.moviestrailer.databinding.ItemMovieVideoBinding;
import com.vincenzocassown.moviestrailer.model.video.ResultVideo;

import java.util.ArrayList;
import java.util.List;

public class MovieVideoAdapter extends RecyclerView.Adapter<MovieVideoAdapter.ViewHolder> {
    private List<ResultVideo> list = new ArrayList<>();
    private Activity activity;

    public MovieVideoAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setList(List<ResultVideo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMovieVideoBinding binding = ItemMovieVideoBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.itemVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, PlayerActivity.class);
                intent.putExtra("key",list.get(position).getKey());
                activity.startActivity(intent);
            }
        });
        holder.binding.setVideo(list.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieVideoBinding binding;
        public ViewHolder(ItemMovieVideoBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

}
