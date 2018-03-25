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
import com.udacity.spyrakis.popularmovies.models.Movie;
import com.udacity.spyrakis.popularmovies.services.OnItemClickListener;

import java.util.ArrayList;


/*
  Created by ounoufrios on 24/3/18.
 */


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<Movie> movies;
    private Context context;
    private OnItemClickListener listener;
    ViewHolder vh;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView image;

        Movie movie;

        public ViewHolder(FrameLayout v) {
            super(v);
            image = v.findViewById(R.id.movieImg);

        }

        public void bind(final int movieId, final OnItemClickListener listener) {
            image.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(movieId);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(ArrayList<Movie> movies, Context context, OnItemClickListener listener) {
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(context);

        FrameLayout movieCell = (FrameLayout) inflater.inflate(R.layout.item_movie_list, parent, false);

        vh = new ViewHolder(movieCell);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String imagePath = context.getString(R.string.icons_base_url) + context.getString(R.string.icon_size_suggested) + movies.get(position).getPosterPath();

        holder.movie = movies.get(position);
        Picasso.with(context).load(imagePath).placeholder(R.drawable.placeholder).into(holder.image);

        holder.bind(movies.get(position).getId(), listener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return movies.size();
    }
}

