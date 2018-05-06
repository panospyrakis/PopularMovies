package com.udacity.spyrakis.popularmovies.models.reviews;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ReviewsList implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<Review> results;

    @SerializedName("total_results")
    private int totalResults;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }

    public List<Review> getResults() {
        return results;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public String toString() {
        return
                "ReviewsList{" +
                        "id = '" + id + '\'' +
                        ",page = '" + page + '\'' +
                        ",total_pages = '" + totalPages + '\'' +
                        ",results = '" + results + '\'' +
                        ",total_results = '" + totalResults + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.page);
        dest.writeInt(this.totalPages);
        dest.writeList(this.results);
        dest.writeInt(this.totalResults);
    }

    public ReviewsList() {
    }

    protected ReviewsList(Parcel in) {
        this.id = in.readInt();
        this.page = in.readInt();
        this.totalPages = in.readInt();
        this.results = new ArrayList<Review>();
        in.readList(this.results, Review.class.getClassLoader());
        this.totalResults = in.readInt();
    }

    public static final Parcelable.Creator<ReviewsList> CREATOR = new Parcelable.Creator<ReviewsList>() {
        @Override
        public ReviewsList createFromParcel(Parcel source) {
            return new ReviewsList(source);
        }

        @Override
        public ReviewsList[] newArray(int size) {
            return new ReviewsList[size];
        }
    };
}