package com.udacity.spyrakis.popularmovies.models.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductionCountriesItem implements Parcelable {

    @SerializedName("iso_3166_1")
    private String iso31661;

    @SerializedName("name")
    private String name;

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return
                "ProductionCountriesItem{" +
                        "iso_3166_1 = '" + iso31661 + '\'' +
                        ",name = '" + name + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.iso31661);
        dest.writeString(this.name);
    }

    public ProductionCountriesItem() {
    }

    protected ProductionCountriesItem(Parcel in) {
        this.iso31661 = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<ProductionCountriesItem> CREATOR = new Parcelable.Creator<ProductionCountriesItem>() {
        @Override
        public ProductionCountriesItem createFromParcel(Parcel source) {
            return new ProductionCountriesItem(source);
        }

        @Override
        public ProductionCountriesItem[] newArray(int size) {
            return new ProductionCountriesItem[size];
        }
    };
}