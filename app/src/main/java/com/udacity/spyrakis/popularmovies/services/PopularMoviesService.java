package com.udacity.spyrakis.popularmovies.services;

import com.udacity.spyrakis.popularmovies.models.MovieDetails;
import com.udacity.spyrakis.popularmovies.models.MovieList;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by ounoufrios on 24/3/18.
 */


public interface PopularMoviesService {

    @GET("{sortBy}")
    Call<MovieList> listMovies(@Path("sortBy") String sortBy, @Query("api_key") String apiKey);

    @GET("{movieId}")
    Call<MovieDetails> movieDetails(@Path("movieId") String sortBy, @Query("api_key") String apiKey);

    @GET("{sortBy}")
    Call<MovieList> listMovies(@Path("sortBy") String sortBy, @Query("api_key") String apiKey, @Query("page") int page);

}