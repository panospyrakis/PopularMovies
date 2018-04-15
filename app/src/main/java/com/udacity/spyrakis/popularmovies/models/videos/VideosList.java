package com.udacity.spyrakis.popularmovies.models.videos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosList {

    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<Video> results;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    public List<Video> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return
                "VideosList{" +
                        "id = '" + id + '\'' +
                        ",results = '" + results + '\'' +
                        "}";
    }
}