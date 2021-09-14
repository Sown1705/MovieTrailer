package com.vincenzocassown.moviestrailer;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vincenzocassown.moviestrailer.adapter.MovieVideoAdapter;
import com.vincenzocassown.moviestrailer.databinding.ActivityTrailerBinding;
import com.vincenzocassown.moviestrailer.model.video.ResultVideo;
import com.vincenzocassown.moviestrailer.model.video.Video;
import com.vincenzocassown.moviestrailer.service.MovieInstance;
import com.vincenzocassown.moviestrailer.service.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

public class TrailerActivity extends AppCompatActivity {
    private ActivityTrailerBinding binding;
    private int idVideo;
    private Retrofit retrofit;
    private MovieService service;
    private List<ResultVideo> resultVideos = new ArrayList<>();
    private MovieVideoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTrailerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        loadData();
    }

    private void loadData() {
        service.getVideo(idVideo).enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                resultVideos.addAll(response.body().getResults());
                adapter.setList(resultVideos);
                binding.rcv.setAdapter(adapter);
                binding.rcv.setHasFixedSize(true);
                binding.rcv.setLayoutManager(new LinearLayoutManager(TrailerActivity.this, RecyclerView.VERTICAL,false));
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {
                Toast.makeText(TrailerActivity.this, "Failed !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        adapter = new MovieVideoAdapter(this);
        retrofit = MovieInstance.getInstance();
        service = retrofit.create(MovieService.class);
        idVideo = getIntent().getIntExtra("id", 0);
    }
}