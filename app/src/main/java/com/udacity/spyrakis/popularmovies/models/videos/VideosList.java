package com.udacity.spyrakis.popularmovies.models.videos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VideosList implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeList(this.results);
    }

    public VideosList() {
    }

    protected VideosList(Parcel in) {
        this.id = in.readInt();
        this.results = new ArrayList<Video>();
        in.readList(this.results, Video.class.getClassLoader());
    }

    public static final Parcelable.Creator<VideosList> CREATOR = new Parcelable.Creator<VideosList>() {
        @Override
        public VideosList createFromParcel(Parcel source) {
            return new VideosList(source);
        }

        @Override
        public VideosList[] newArray(int size) {
            return new VideosList[size];
        }
    };
}