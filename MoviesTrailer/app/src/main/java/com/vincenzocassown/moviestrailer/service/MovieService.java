package com.vincenzocassown.moviestrailer.service;


import com.vincenzocassown.moviestrailer.model.detail_movie.DetailMovie;
import com.vincenzocassown.moviestrailer.model.popular.MoviePopular;
import com.vincenzocassown.moviestrailer.model.review.ReviewMovie;
import com.vincenzocassown.moviestrailer.model.topreated.MovieTopRate;
import com.vincenzocassown.moviestrailer.model.upcoming_nowplaying.MovieUpNow;
import com.vincenzocassown.moviestrailer.model.video.ResultVideo;
import com.vincenzocassown.moviestrailer.model.video.Video;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    // get movie popular
    @GET("3/movie/popular?api_key=35ce11dc766c9d982a2e0465ecbbdc4e&language=en-US")
    Call<MoviePopular> getMoviePopular(@Query("page")int page);
    // get movie top rated
    @GET("3/movie/top_rated?api_key=35ce11dc766c9d982a2e0465ecbbdc4e&language=en-US")
    Call<MovieTopRate> getMovieTopRated(@Query("page")int page);
    // get movie upcoming
    @GET("3/movie/upcoming?api_key=35ce11dc766c9d982a2e0465ecbbdc4e&language=en-US")
    Call<MovieUpNow> getMovieUpComing(@Query("page")int page);
    // get movie now playing
    @GET("3/movie/now_playing?api_key=35ce11dc766c9d982a2e0465ecbbdc4e&language=en-US")
    Call<MovieUpNow> getMovieNowPlaying(@Query("page")int page);
    //get detail movie
    @GET("3/movie/{movie_id}?api_key=35ce11dc766c9d982a2e0465ecbbdc4e&language=en-US")
    Call<DetailMovie> getDetailMovie(@Path("movie_id") int movie_id);
    //get review movie
    @GET("3/movie/{movie_id}/reviews?api_key=35ce11dc766c9d982a2e0465ecbbdc4e&language=en-US")
    Call<ReviewMovie> getReviewMovie(@Path("movie_id") int movie_id,
                                     @Query("page") int page);
    //get Video
    @GET("3/movie/{movie_id}/videos?api_key=35ce11dc766c9d982a2e0465ecbbdc4e&language=en-US")
    Call<Video> getVideo(@Path("movie_id") int movie_id );

}
