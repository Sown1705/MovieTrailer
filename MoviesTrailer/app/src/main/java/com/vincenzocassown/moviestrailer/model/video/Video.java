package com.vincenzocassown.moviestrailer.model.video;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<ResultVideo> resultVideos = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ResultVideo> getResults() {
        return resultVideos;
    }

    public void setResults(List<ResultVideo> resultVideos) {
        this.resultVideos = resultVideos;
    }

}
