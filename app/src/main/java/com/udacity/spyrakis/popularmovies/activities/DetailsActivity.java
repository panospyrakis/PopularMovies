package com.udacity.spyrakis.popularmovies.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.models.movies.MovieDetails;
import com.udacity.spyrakis.popularmovies.services.PopularMoviesService;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {


    @BindView(R.id.details_title)
    TextView detailsTitle;
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.date)
    TextView releaseDate;
    @BindView(R.id.duration)
    TextView duration;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.shortDescription)
    TextView shortDescription;

    String movieId;
    PopularMoviesService service;
    ProgressDialog progress;
    MovieDetails movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        movieId = getIntent().getIntExtra(MainActivity.EXTRA_MOVIE_ID,0)+"";
        setUpNetworkCalls();
        getDetails();
    }

    private void setUpContent() {

        detailsTitle.setText(movie.getTitle());
        releaseDate.setText(movie.getReleaseDate().substring(0, 4));
        duration.setText(getApplicationContext().getString(R.string.duration, movie.getRuntime()));
        detailsTitle.setText(movie.getTitle());
        score.setText(getApplicationContext().getString(R.string.vote_average, movie.getVoteAverage()+""));
        shortDescription.setText(movie.getOverview());
        String imagePath = getApplicationContext().getString(R.string.icons_base_url) + getApplicationContext().getString(R.string.icon_size_suggested) + movie.getPosterPath();

        Picasso.with(getApplicationContext()).load(imagePath).placeholder(R.drawable.placeholder).into(poster);

    }

    private void getDetails() {
        String apiKey = getApplicationContext().getString(R.string.api_key);

        progress = new ProgressDialog(this);
        progress.setTitle(getApplicationContext().getString(R.string.loading));
        progress.setMessage(getApplicationContext().getString(R.string.wait));
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        Call<MovieDetails> detailsCall = service.movieDetails(movieId, apiKey);


        detailsCall.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                progress.dismiss();
                movie = response.body();
                setUpContent();
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });

    }

    private void setUpNetworkCalls() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getApplicationContext().getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(PopularMoviesService.class);
    }
}
