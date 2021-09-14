package com.vincenzocassown.moviestrailer.fragment;

import android.os.Bundle;

import android.os.Handler;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.vincenzocassown.moviestrailer.adapter.MoviePopularAdapter;
import com.vincenzocassown.moviestrailer.databinding.FragmentPopularBinding;
import com.vincenzocassown.moviestrailer.model.popular.MoviePopular;
import com.vincenzocassown.moviestrailer.model.popular.Result;
import com.vincenzocassown.moviestrailer.other.EndlessRecyclerViewScrollListener;
import com.vincenzocassown.moviestrailer.service.MovieInstance;
import com.vincenzocassown.moviestrailer.service.MovieService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopularFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PopularFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PopularFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopularFragment newInstance(String param1, String param2) {
        PopularFragment fragment = new PopularFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private MoviePopularAdapter adapter;
    private FragmentPopularBinding binding;
    private int pageNumber = 1;
    private LinearLayoutManager layoutManager;
    private List<Result> resultList;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPopularBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        resultList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        loadData(pageNumber);
        loadMore();
        refresh();
        // Inflate the layout for this fragment
        return view;
    }

    private void refresh() {
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(pageNumber);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Stop animation (This will be after 3 seconds)
                        binding.swipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        binding.swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void loadMore() {

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadData(page);
                // Restore state
                Toast.makeText(getActivity(), "Load more !", Toast.LENGTH_SHORT).show();
            }
        };
        binding.rcv.addOnScrollListener(scrollListener);
    }

    private void loadData(int page) {
        Retrofit retrofit = MovieInstance.getInstance();
        MovieService service = retrofit.create(MovieService.class);
        adapter = new MoviePopularAdapter();
        service.getMoviePopular(page).enqueue(new Callback<MoviePopular>() {
            @Override
            public void onResponse(Call<MoviePopular> call, Response<MoviePopular> response) {
                resultList.addAll(response.body().getResults());
                adapter.setList(resultList);
                binding.rcv.setHasFixedSize(true);
                binding.rcv.setLayoutManager(layoutManager);
                binding.rcv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MoviePopular> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed !", Toast.LENGTH_SHORT).show();
            }
        });
    }

}