package com.udacity.spyrakis.popularmovies.models.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SpokenLanguagesItem implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("iso_639_1")
    private String iso6391;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getIso6391() {
        return iso6391;
    }

    @Override
    public String toString() {
        return
                "SpokenLanguagesItem{" +
                        "name = '" + name + '\'' +
                        ",iso_639_1 = '" + iso6391 + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.iso6391);
    }

    public SpokenLanguagesItem() {
    }

    protected SpokenLanguagesItem(Parcel in) {
        this.name = in.readString();
        this.iso6391 = in.readString();
    }

    public static final Parcelable.Creator<SpokenLanguagesItem> CREATOR = new Parcelable.Creator<SpokenLanguagesItem>() {
        @Override
        public SpokenLanguagesItem createFromParcel(Parcel source) {
            return new SpokenLanguagesItem(source);
        }

        @Override
        public SpokenLanguagesItem[] newArray(int size) {
            return new SpokenLanguagesItem[size];
        }
    };
}