package com.udacity.spyrakis.popularmovies.fragments;

/*
  Created by ounoufrios on 22/3/18.
 */

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.activities.DetailsActivity;
import com.udacity.spyrakis.popularmovies.activities.MainActivity;
import com.udacity.spyrakis.popularmovies.adapters.CursorListAdapter;
import com.udacity.spyrakis.popularmovies.data.MoviesContract;
import com.udacity.spyrakis.popularmovies.services.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavouritesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String EXTRA_GRID_STATE = "EXTRA_GRID_STATE";

    private static final int CURSOR_LOADER_ID = 0;
    @BindView(R.id.favourites_list)
    GridView favouritesList;

    public FavouritesFragment() {
    }


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FavouritesFragment newInstance(int sectionNumber) {
        FavouritesFragment fragment = new FavouritesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);
        ButterKnife.bind(this, rootView);

        OnItemClickListener listener = new OnItemClickListener() {
            @Override
            public void onItemClick(int movieId) {
                Intent detailsIntent = new Intent(getActivity(), DetailsActivity.class);
                detailsIntent.putExtra(MainActivity.EXTRA_MOVIE_ID, movieId); //Optional parameters
                getContext().startActivity(detailsIntent);
            }
        };
        Cursor cursor =
                getActivity().getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
        getLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
        if (cursor.getCount() == 0) {
            return rootView;
        }

        CursorListAdapter adapter = new CursorListAdapter(getContext(), cursor, CURSOR_LOADER_ID, listener);

        favouritesList.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_GRID_STATE,favouritesList.onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null){
            favouritesList.onRestoreInstanceState(savedInstanceState.getParcelable(EXTRA_GRID_STATE));
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(getActivity(),
                MoviesContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
