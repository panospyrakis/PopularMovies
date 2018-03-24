package com.udacity.spyrakis.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.models.MovieList;
import com.udacity.spyrakis.popularmovies.models.ResultsItem;

import java.util.ArrayList;


/**
 * Created by ounoufrios on 24/3/18.
 */


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<ResultsItem> movies;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView image;

        public ViewHolder(FrameLayout v) {
            super(v);
            image = v.findViewById(R.id.movieImg);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(ArrayList<ResultsItem> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(context);

        FrameLayout movieCell = (FrameLayout) inflater.inflate(R.layout.item_movie_list, parent, false);


        ViewHolder vh = new ViewHolder(movieCell);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String imagePath = context.getString(R.string.icons_base_url) + context.getString(R.string.icon_size_suggested) + movies.get(position).getPosterPath();
        Picasso.with(context).load(imagePath).into(holder.image);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return movies.size();
    }
}

