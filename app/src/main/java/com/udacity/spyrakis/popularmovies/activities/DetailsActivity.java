package com.udacity.spyrakis.popularmovies.activities;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.adapters.ReviewsAdapter;
import com.udacity.spyrakis.popularmovies.adapters.TrailerListAdapter;
import com.udacity.spyrakis.popularmovies.models.movies.MovieDetails;
import com.udacity.spyrakis.popularmovies.models.reviews.ReviewsList;
import com.udacity.spyrakis.popularmovies.models.videos.VideosList;
import com.udacity.spyrakis.popularmovies.services.OnTrailerItemClick;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends BaseActivity {


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
    @BindView(R.id.reviewList)
    RecyclerView reviewsList;
    @BindView(R.id.reviews_title)
    TextView reviewsTitle;

    String movieId;
    ProgressDialog progress;
    MovieDetails movie;
    boolean detailsReturned = false;
    boolean videosReturned = false;
    boolean reviewsReturned = false;
    VideosList videos;
    ReviewsList reviews;

    public static final String EXTRA_TRAILERS = "EXTRA_TRAILERS";
    public static final String EXTRA_REVIEWS = "EXTRA_REVIEWS";
    public static final String EXTRA_DETAILS = "EXTRA_DETAILS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        movieId = getIntent().getIntExtra(MainActivity.EXTRA_MOVIE_ID,0)+"";
        setUpNetworkCalls();
        makeSomeCalls();
    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getParcelable(EXTRA_TRAILERS) != null &&
                savedInstanceState.getParcelable(EXTRA_REVIEWS) != null &&
                savedInstanceState.getParcelable(EXTRA_DETAILS) != null){
            reviews = savedInstanceState.getParcelable(EXTRA_REVIEWS);
            videos = savedInstanceState.getParcelable(EXTRA_TRAILERS);
            movie = savedInstanceState.getParcelable(EXTRA_DETAILS);
            setUpTrailerList();
            setUpReviewsList();
        }else{
            makeSomeCalls();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_TRAILERS,videos);
        outState.putParcelable(EXTRA_REVIEWS,reviews);
        outState.putParcelable(EXTRA_DETAILS,movie);
    }

    @Override
    public void onStart() {
        if (progress != null && progress.isShowing()){
            progress.dismiss();
        }
        makeSomeCalls();
        super.onStart();
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
        setUpReviewsList();

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

    private void setUpReviewsList(){
        if (reviews == null || reviews.getResults().isEmpty()){
            reviewsList.setVisibility(View.GONE);
            reviewsTitle.setVisibility(View.GONE);
            return;
        }

        ReviewsAdapter adapter = new ReviewsAdapter(reviews.getResults(), getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        this.reviewsList.setLayoutManager(layoutManager);
        this.reviewsList.setAdapter(adapter);
    }

    private void getDetails() {

        Call<MovieDetails> detailsCall = service.movieDetails(movieId, apiKey);
        detailsReturned = false;

        detailsCall.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                detailsReturned = true;
                if (!isActive) return;

                movie = response.body();
                if (videosReturned && reviewsReturned){
                    progress.dismiss();
                    setUpContent();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t) {
                progress.dismiss();
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
                if (!isActive) return;

                videos = response.body();
                if (detailsReturned && reviewsReturned){
                    progress.dismiss();
                    setUpContent();
                }
            }

            @Override
            public void onFailure(@NonNull Call<VideosList> call, @NonNull Throwable t) {
                progress.dismiss();
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


}
