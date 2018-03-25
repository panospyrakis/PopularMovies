package com.udacity.spyrakis.popularmovies.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.adapters.CustomSpinnerAdapter;
import com.udacity.spyrakis.popularmovies.fragments.MainFragment;
import com.udacity.spyrakis.popularmovies.models.MovieList;
import com.udacity.spyrakis.popularmovies.services.PopularMoviesService;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner)
    Spinner spinner;

    PopularMoviesService service;

    MovieList movieListToDisplay;

    int selectedPosition = 0;

    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setupSpinner();

        setUpNetworkCalls();
    }

    private void populateList(String sortBy) {
        String apiKey = getApplicationContext().getString(R.string.api_key);

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        Call<MovieList> listCall = service.listMovies(sortBy, apiKey);


        listCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                movieListToDisplay = response.body();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance(selectedPosition, movieListToDisplay))
                        .commit();
                progress.dismiss();

            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

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

    private void setupSpinner() {
        // Setup spinner
        spinner.setAdapter(new CustomSpinnerAdapter(
                toolbar.getContext(),
                new String[]{
                        getApplicationContext().getString(R.string.popular),
                        getApplicationContext().getString(R.string.topRated),
                }));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                selectedPosition = position + 1;
                if (selectedPosition == 1){
                    populateList(getApplicationContext().getString(R.string.sortByPopular));
                }else{
                    populateList(getApplicationContext().getString(R.string.sortByTopRated));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
