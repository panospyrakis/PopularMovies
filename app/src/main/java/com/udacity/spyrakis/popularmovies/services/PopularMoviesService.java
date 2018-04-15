package com.udacity.spyrakis.popularmovies.services;

import com.udacity.spyrakis.popularmovies.models.movies.MovieDetails;
import com.udacity.spyrakis.popularmovies.models.movies.MovieList;
import com.udacity.spyrakis.popularmovies.models.reviews.ReviewsList;
import com.udacity.spyrakis.popularmovies.models.videos.VideosList;

import retrofit2.Call;
import retrofit2.http.*;

/*
  Created by ounoufrios on 24/3/18.
 */


public interface PopularMoviesService {

    @GET("{sortBy}")
    Call<MovieList> listMovies(@Path("sortBy") String sortBy, @Query("api_key") String apiKey);

    @GET("{movieId}")
    Call<MovieDetails> movieDetails(@Path("movieId") String sortBy, @Query("api_key") String apiKey);

    @GET("{sortBy}")
    Call<MovieList> listMovies(@Path("sortBy") String sortBy, @Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{movieId}/videos")
    Call<VideosList> videos(@Path("movieId") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movieId}/reviews")
    Call<ReviewsList> reviews(@Path("movieId") int movieId, @Query("api_key") String apiKey);

}