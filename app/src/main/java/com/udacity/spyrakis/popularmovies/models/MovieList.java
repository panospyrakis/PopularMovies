package com.udacity.spyrakis.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class MovieList implements Parcelable {

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private ArrayList<Movie> movieList;

	@SerializedName("total_results")
	private int totalResults;

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setResults(ArrayList<Movie> movieList){
		this.movieList = movieList;
	}

	public ArrayList<Movie> getResults(){
		return movieList;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	@Override
 	public String toString(){
		return 
			"MovieList{" + 
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + movieList + '\'' +
			",total_results = '" + totalResults + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.page);
		dest.writeInt(this.totalPages);
		dest.writeTypedList(this.movieList);
		dest.writeInt(this.totalResults);
	}

	public MovieList() {
	}

	protected MovieList(Parcel in) {
		this.page = in.readInt();
		this.totalPages = in.readInt();
		this.movieList = in.createTypedArrayList(Movie.CREATOR);
		this.totalResults = in.readInt();
	}

	public static final Parcelable.Creator<MovieList> CREATOR = new Parcelable.Creator<MovieList>() {
		@Override
		public MovieList createFromParcel(Parcel source) {
			return new MovieList(source);
		}

		@Override
		public MovieList[] newArray(int size) {
			return new MovieList[size];
		}
	};
}