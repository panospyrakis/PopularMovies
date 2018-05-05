package com.udacity.spyrakis.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.models.videos.Video;
import com.udacity.spyrakis.popularmovies.services.OnTrailerItemClick;

import java.util.List;

/**
 * Created by pspyrakis on 5/5/18.
 */
public class TrailerListAdapter extends RecyclerView.Adapter<TrailerListAdapter.ViewHolder> {
    private List<Video> trailers;
    private Context context;
    private OnTrailerItemClick listener;
    private ViewHolder vh;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout container;
        public TextView trailerTitle;

        Video trailer;

        private ViewHolder(LinearLayout v) {
            super(v);
            container = v;
            trailerTitle = v.findViewById(R.id.trailer_name);
        }

        private void bind(final String youtubeKey, final OnTrailerItemClick listener) {

            trailerTitle.setText(trailer.getName());
            container.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(youtubeKey);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TrailerListAdapter(List<Video> trailers, Context context, OnTrailerItemClick listener) {
        this.trailers = trailers;
        this.context = context;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TrailerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(context);

        LinearLayout movieCell = (LinearLayout) inflater.inflate(R.layout.item_movie_trailer, parent, false);

        vh = new ViewHolder(movieCell);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String youtubeKey = trailers.get(position).getKey();

        holder.trailer = trailers.get(position);

        holder.bind(youtubeKey, listener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trailers.size();
    }
}
