package com.udacity.spyrakis.popularmovies.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import com.facebook.stetho.Stetho;
import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.adapters.CustomSpinnerAdapter;
import com.udacity.spyrakis.popularmovies.fragments.FavouritesFragment;
import com.udacity.spyrakis.popularmovies.fragments.MainFragment;
import com.udacity.spyrakis.popularmovies.models.movies.MovieList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {

    public static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";
    public static final String EXTRA_MOVIES = "EXTRA_MOVIES";
    public static final String EXTRA_SELECTED_TAB = "EXTRA_SELECTED_TAB";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner)
    Spinner spinner;

    MovieList movieListToDisplay;

    int selectedPosition = 1;

    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setUpNetworkCalls();

        setupSpinner();
    }

    @Override
    public void onStart() {
        if (progress != null && progress.isShowing()){
            progress.dismiss();
        }
        setupSpinner();
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getParcelable(EXTRA_MOVIES) != null){
            movieListToDisplay = savedInstanceState.getParcelable(EXTRA_MOVIES);
            selectedPosition = savedInstanceState.getInt(EXTRA_SELECTED_TAB,1);
            setupSpinner();
        }else{
             setupSpinner();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_SELECTED_TAB,selectedPosition);
        outState.putParcelable(EXTRA_MOVIES,movieListToDisplay);
    }

    private void showFavourites(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FavouritesFragment.newInstance(selectedPosition))
                .commit();
    }

    private void populateList(String sortBy) {

        progress = new ProgressDialog(this);
        progress.setTitle(getApplicationContext().getString(R.string.loading));
        progress.setMessage(getApplicationContext().getString(R.string.wait));
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        Call<MovieList> listCall = service.listMovies(sortBy, apiKey);


        listCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                if (!isActive) return;

                movieListToDisplay = response.body();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance(selectedPosition, movieListToDisplay))
                        .commit();
                progress.dismiss();


            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                progress.dismiss();
            }
        });

    }


    private void setupSpinner() {
        // Setup spinner
        spinner.setAdapter(new CustomSpinnerAdapter(
                toolbar.getContext(),
                new String[]{
                        getApplicationContext().getString(R.string.popular),
                        getApplicationContext().getString(R.string.topRated),
                        getApplicationContext().getString(R.string.favourite)
                }));
        spinner.setSelection(selectedPosition-1);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // When the given dropdown item is selected, show its contents in the
                // container view.
                selectedPosition = position + 1;
                if (selectedPosition == 1){
                    populateList(getApplicationContext().getString(R.string.sortByPopular));
                }else if (selectedPosition == 2){
                    populateList(getApplicationContext().getString(R.string.sortByTopRated));
                }else{
                    showFavourites();
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


