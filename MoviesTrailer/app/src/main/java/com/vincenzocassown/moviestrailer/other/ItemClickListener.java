package com.vincenzocassown.moviestrailer.other;


import com.vincenzocassown.moviestrailer.model.popular.Result;

public interface  ItemClickListener {
    void ItemPopularClick(Result result);
    void ItemTopRateClick(com.vincenzocassown.moviestrailer.model.topreated.Result result);
    void ItemUpNowClick(com.vincenzocassown.moviestrailer.model.upcoming_nowplaying.Result result);
}
