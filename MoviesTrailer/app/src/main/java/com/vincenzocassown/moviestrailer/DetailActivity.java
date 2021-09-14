package com.vincenzocassown.moviestrailer;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.vincenzocassown.moviestrailer.adapter.MovieReviewAdapter;
import com.vincenzocassown.moviestrailer.databinding.ActivityDetailBinding;
import com.vincenzocassown.moviestrailer.model.detail_movie.DetailMovie;
import com.vincenzocassown.moviestrailer.model.review.ResultReview;
import com.vincenzocassown.moviestrailer.model.review.ReviewMovie;
import com.vincenzocassown.moviestrailer.service.MovieInstance;
import com.vincenzocassown.moviestrailer.service.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private MovieService service;
    private ActivityDetailBinding binding;
    private DetailMovie detailMovie;
    private int pagerNumberReview =1;
    private MovieReviewAdapter adapter;
    private List<ResultReview> reviewList = new ArrayList<>();
    private int idMovie =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = ActivityDetailBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

       adapter = new MovieReviewAdapter();

        retrofit = MovieInstance.getInstance();
        service = retrofit.create(MovieService.class);

        loadData();
        binding.imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,TrailerActivity.class);
                intent.putExtra("id",idMovie);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        idMovie = getIntent().getIntExtra("id",0);
        //Log.e("ID",""+idMovie);
        service.getDetailMovie(idMovie).enqueue(new Callback<DetailMovie>() {
            @Override
            public void onResponse(Call<DetailMovie> call, Response<DetailMovie> response) {
                detailMovie=response.body();

                Glide.with(DetailActivity.this).load("https://image.tmdb.org/t/p/w500"+detailMovie.getBackdropPath()).into(binding.imageView2);
                binding.tvTitle.setText(detailMovie.getTitle());
                binding.tvAverage.setText(""+detailMovie.getVoteAverage());
                binding.tvOverview.setText(detailMovie.getOverview());
            }

            @Override
            public void onFailure(Call<DetailMovie> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Failed !", Toast.LENGTH_SHORT).show();
                Log.e("ERROR",""+t.getMessage());
            }
        });


        service.getReviewMovie(idMovie,pagerNumberReview).enqueue(new Callback<ReviewMovie>() {
            @Override
            public void onResponse(Call<ReviewMovie> call, Response<ReviewMovie> response) {
                reviewList.addAll(response.body().getResults());
                if (reviewList==null){
                    binding.tvReview.setHint("No Comment");
                }
                adapter.setResultReviewList(reviewList);
                binding.rcv.setAdapter(adapter);
                binding.rcv.setHasFixedSize(true);
                binding.rcv.setLayoutManager(new LinearLayoutManager(DetailActivity.this, RecyclerView.VERTICAL,false));
                Log.e("rp",""+reviewList);
            }

            @Override
            public void onFailure(Call<ReviewMovie> call, Throwable t) {
                Log.e("ERROR",""+t.getMessage());
            }
        });
    }
}