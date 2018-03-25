package com.udacity.spyrakis.popularmovies.fragments;

/**
 * Created by ounoufrios on 22/3/18.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.adapters.ListAdapter;
import com.udacity.spyrakis.popularmovies.models.MovieList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_MOVIE_LIST = "movie_list";

    @BindView(R.id.movie_List)
    RecyclerView listView;

    public MainFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MainFragment newInstance(int sectionNumber, MovieList listToDisplay) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putParcelable(ARG_MOVIE_LIST, listToDisplay);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        MovieList listToDisplay = getArguments().getParcelable(ARG_MOVIE_LIST);

        if(listToDisplay != null) {
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
            listView.setLayoutManager(layoutManager);

            ListAdapter adapter = new ListAdapter(listToDisplay.getResults(), getContext());
            listView.setAdapter(adapter);
        }
        return rootView;
    }
}
