package com.vincenzocassown.moviestrailer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.vincenzocassown.moviestrailer.databinding.ItemReviewBinding;
import com.vincenzocassown.moviestrailer.model.review.ResultReview;

import java.util.ArrayList;
import java.util.List;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.ViewHolder> {
    private List<ResultReview> resultReviewList = new ArrayList<>();

    public void setResultReviewList(List<ResultReview> resultReviewList) {
        this.resultReviewList = resultReviewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemReviewBinding binding = ItemReviewBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setReview(resultReviewList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (resultReviewList !=null)return resultReviewList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemReviewBinding binding;
        public ViewHolder(ItemReviewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
