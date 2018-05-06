package com.udacity.spyrakis.popularmovies.models.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails implements Parcelable {

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("video")
    private boolean video;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("revenue")
    private int revenue;

    @SerializedName("genres")
    private List<GenresItem> genres;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("production_countries")
    private List<ProductionCountriesItem> productionCountries;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("budget")
    private int budget;

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("spoken_languages")
    private List<SpokenLanguagesItem> spokenLanguages;

    @SerializedName("production_companies")
    private List<ProductionCompaniesItem> productionCompanies;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("belongs_to_collection")
    private BelongsToCollection belongsToCollection;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("status")
    private String status;

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public boolean isVideo() {
        return video;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setGenres(List<GenresItem> genres) {
        this.genres = genres;
    }

    public List<GenresItem> getGenres() {
        return genres;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setProductionCountries(List<ProductionCountriesItem> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<ProductionCountriesItem> getProductionCountries() {
        return productionCountries;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getBudget() {
        return budget;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setSpokenLanguages(List<SpokenLanguagesItem> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public List<SpokenLanguagesItem> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCompaniesItem> getProductionCompanies() {
        return productionCompanies;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTagline() {
        return tagline;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "MovieDetails{" +
                        "original_language = '" + originalLanguage + '\'' +
                        ",imdb_id = '" + imdbId + '\'' +
                        ",video = '" + video + '\'' +
                        ",title = '" + title + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",revenue = '" + revenue + '\'' +
                        ",genres = '" + genres + '\'' +
                        ",popularity = '" + popularity + '\'' +
                        ",production_countries = '" + productionCountries + '\'' +
                        ",id = '" + id + '\'' +
                        ",vote_count = '" + voteCount + '\'' +
                        ",budget = '" + budget + '\'' +
                        ",overview = '" + overview + '\'' +
                        ",original_title = '" + originalTitle + '\'' +
                        ",runtime = '" + runtime + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",spoken_languages = '" + spokenLanguages + '\'' +
                        ",production_companies = '" + productionCompanies + '\'' +
                        ",release_date = '" + releaseDate + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",belongs_to_collection = '" + belongsToCollection + '\'' +
                        ",tagline = '" + tagline + '\'' +
                        ",adult = '" + adult + '\'' +
                        ",homepage = '" + homepage + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalLanguage);
        dest.writeString(this.imdbId);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeString(this.backdropPath);
        dest.writeInt(this.revenue);
        dest.writeTypedList(this.genres);
        dest.writeDouble(this.popularity);
        dest.writeList(this.productionCountries);
        dest.writeInt(this.id);
        dest.writeInt(this.voteCount);
        dest.writeInt(this.budget);
        dest.writeString(this.overview);
        dest.writeString(this.originalTitle);
        dest.writeInt(this.runtime);
        dest.writeString(this.posterPath);
        dest.writeList(this.spokenLanguages);
        dest.writeList(this.productionCompanies);
        dest.writeString(this.releaseDate);
        dest.writeDouble(this.voteAverage);
        dest.writeParcelable(this.belongsToCollection, flags);
        dest.writeString(this.tagline);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.homepage);
        dest.writeString(this.status);
    }

    public MovieDetails() {
    }

    protected MovieDetails(Parcel in) {
        this.originalLanguage = in.readString();
        this.imdbId = in.readString();
        this.video = in.readByte() != 0;
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.revenue = in.readInt();
        this.genres = in.createTypedArrayList(GenresItem.CREATOR);
        this.popularity = in.readDouble();
        this.productionCountries = new ArrayList<ProductionCountriesItem>();
        in.readList(this.productionCountries, ProductionCountriesItem.class.getClassLoader());
        this.id = in.readInt();
        this.voteCount = in.readInt();
        this.budget = in.readInt();
        this.overview = in.readString();
        this.originalTitle = in.readString();
        this.runtime = in.readInt();
        this.posterPath = in.readString();
        this.spokenLanguages = new ArrayList<SpokenLanguagesItem>();
        in.readList(this.spokenLanguages, SpokenLanguagesItem.class.getClassLoader());
        this.productionCompanies = new ArrayList<ProductionCompaniesItem>();
        in.readList(this.productionCompanies, ProductionCompaniesItem.class.getClassLoader());
        this.releaseDate = in.readString();
        this.voteAverage = in.readDouble();
        this.belongsToCollection = in.readParcelable(Object.class.getClassLoader());
        this.tagline = in.readString();
        this.adult = in.readByte() != 0;
        this.homepage = in.readParcelable(Object.class.getClassLoader());
        this.status = in.readString();
    }

    public static final Parcelable.Creator<MovieDetails> CREATOR = new Parcelable.Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel source) {
            return new MovieDetails(source);
        }

        @Override
        public MovieDetails[] newArray(int size) {
            return new MovieDetails[size];
        }
    };
}