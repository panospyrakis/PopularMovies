package com.udacity.spyrakis.popularmovies.models.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductionCompaniesItem implements Parcelable {

    @SerializedName("logo_path")
    private String logoPath;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("origin_country")
    private String originCountry;

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getLogoPath() {
        return logoPath;
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

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    @Override
    public String toString() {
        return
                "ProductionCompaniesItem{" +
                        "logo_path = '" + logoPath + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",origin_country = '" + originCountry + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.logoPath);
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeString(this.originCountry);
    }

    public ProductionCompaniesItem() {
    }

    protected ProductionCompaniesItem(Parcel in) {
        this.logoPath = in.readString();
        this.name = in.readString();
        this.id = in.readInt();
        this.originCountry = in.readString();
    }

    public static final Parcelable.Creator<ProductionCompaniesItem> CREATOR = new Parcelable.Creator<ProductionCompaniesItem>() {
        @Override
        public ProductionCompaniesItem createFromParcel(Parcel source) {
            return new ProductionCompaniesItem(source);
        }

        @Override
        public ProductionCompaniesItem[] newArray(int size) {
            return new ProductionCompaniesItem[size];
        }
    };
}