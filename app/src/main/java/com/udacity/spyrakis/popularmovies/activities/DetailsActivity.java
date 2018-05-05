package com.udacity.spyrakis.popularmovies.activities;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.spyrakis.popularmovies.BuildConfig;
import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.adapters.TrailerListAdapter;
import com.udacity.spyrakis.popularmovies.models.movies.MovieDetails;
import com.udacity.spyrakis.popularmovies.models.reviews.ReviewsList;
import com.udacity.spyrakis.popularmovies.models.videos.VideosList;
import com.udacity.spyrakis.popularmovies.services.OnTrailerItemClick;
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
    @BindView(R.id.details_container)
    LinearLayout detailsContainer;
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
    @BindView(R.id.trailerList)
    RecyclerView trailerList;
    @BindView(R.id.trailers_title)
    TextView trailersTitle;

    String movieId;
    PopularMoviesService service;
    ProgressDialog progress;
    MovieDetails movie;
    String apiKey = BuildConfig.THE_MOVIE_DB_API_KEY;
    boolean detailsReturned = false;
    boolean videosReturned = false;
    boolean reviewsReturned = false;
    VideosList videos;
    ReviewsList reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        movieId = getIntent().getIntExtra(MainActivity.EXTRA_MOVIE_ID,0)+"";
        setUpNetworkCalls();
        makeSomeCalls();
    }

    private void makeSomeCalls(){
        progress = new ProgressDialog(this);
        progress.setTitle(getApplicationContext().getString(R.string.loading));
        progress.setMessage(getApplicationContext().getString(R.string.wait));
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        getDetails();
        getVideos();
        getReviews();
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

        setUpTrailerList();

    }

    private void setUpTrailerList(){
        if (videos == null || videos.getResults().isEmpty()){
            trailerList.setVisibility(View.GONE);
            trailersTitle.setVisibility(View.GONE);
            return;
        }
        OnTrailerItemClick listener = new OnTrailerItemClick() {
            @Override
            public void onItemClick(String id) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + id));
                try {
                    getApplicationContext().startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    getApplicationContext().startActivity(webIntent);
                }
            }
        };

        TrailerListAdapter adapter = new TrailerListAdapter(videos.getResults(), getApplicationContext(), listener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        this.trailerList.setLayoutManager(layoutManager);
        this.trailerList.setAdapter(adapter);
    }

    private void getDetails() {

        Call<MovieDetails> detailsCall = service.movieDetails(movieId, apiKey);
        detailsReturned = false;

        detailsCall.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                detailsReturned = true;

                movie = response.body();
                if (videosReturned && reviewsReturned){
                    progress.dismiss();
                    setUpContent();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t) {

            }
        });

    }

    private void getVideos() {

        Call<VideosList> videosCall = service.videos(movieId, apiKey);
        videosReturned = false;

        videosCall.enqueue(new Callback<VideosList>() {
            @Override
            public void onResponse(@NonNull Call<VideosList> call, @NonNull Response<VideosList> response) {
                videosReturned = true;

                videos = response.body();
                if (detailsReturned && reviewsReturned){
                    progress.dismiss();
                    setUpContent();
                }
            }

            @Override
            public void onFailure(@NonNull Call<VideosList> call, @NonNull Throwable t) {

            }
        });

    }

    private void getReviews() {

        Call<ReviewsList> videosCall = service.reviews(movieId, apiKey);
        videosReturned = false;

        videosCall.enqueue(new Callback<ReviewsList>() {
            @Override
            public void onResponse(@NonNull Call<ReviewsList> call, @NonNull Response<ReviewsList> response) {
                reviewsReturned = true;
                reviews = response.body();
                if (detailsReturned && videosReturned){
                    progress.dismiss();
                    setUpContent();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReviewsList> call, @NonNull Throwable t) {

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
