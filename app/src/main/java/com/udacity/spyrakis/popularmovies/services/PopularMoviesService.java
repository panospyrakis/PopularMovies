package com.udacity.spyrakis.popularmovies.services;

import com.udacity.spyrakis.popularmovies.models.MovieList;

import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by ounoufrios on 24/3/18.
 */


public interface PopularMoviesService {

    @GET("movie/{sortBy}/")
    Call<MovieList> listMovies(@Path("sortBy") String sortBy, @Query("api_key") String apiKey);

    @GET("movie/{sortBy}/")
    Call<MovieList> listMovies(@Path("sortBy") String sortBy, @Query("api_key") String apiKey, @Query("page") int page);

}