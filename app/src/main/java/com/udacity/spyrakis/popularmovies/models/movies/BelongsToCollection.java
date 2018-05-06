package com.udacity.spyrakis.popularmovies.models.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BelongsToCollection implements Parcelable {

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("poster_path")
    private String posterPath;

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    @Override
    public String toString() {
        return
                "BelongsToCollection{" +
                        "backdrop_path = '" + backdropPath + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backdropPath);
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeString(this.posterPath);
    }

    public BelongsToCollection() {
    }

    protected BelongsToCollection(Parcel in) {
        this.backdropPath = in.readString();
        this.name = in.readString();
        this.id = in.readInt();
        this.posterPath = in.readString();
    }

    public static final Parcelable.Creator<BelongsToCollection> CREATOR = new Parcelable.Creator<BelongsToCollection>() {
        @Override
        public BelongsToCollection createFromParcel(Parcel source) {
            return new BelongsToCollection(source);
        }

        @Override
        public BelongsToCollection[] newArray(int size) {
            return new BelongsToCollection[size];
        }
    };
}