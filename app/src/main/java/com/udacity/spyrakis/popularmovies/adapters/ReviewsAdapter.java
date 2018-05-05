package com.udacity.spyrakis.popularmovies.adapters;

        import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.models.reviews.Review;

import java.util.List;

/**
 * Created by pspyrakis on 5/5/18.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    private List<Review> reviews;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView reviewTitle;
        TextView reviewText;

        Review review;

        private ViewHolder(LinearLayout v) {
            super(v);
            reviewTitle = v.findViewById(R.id.review_title);
            reviewText = v.findViewById(R.id.review_text);
        }

        private void bind() {

            reviewTitle.setText(review.getAuthor());
            reviewText.setText(review.getContent());

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReviewsAdapter(List<Review> reviews, Context context) {
        this.reviews = reviews;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(context);

        LinearLayout movieCell = (LinearLayout) inflater.inflate(R.layout.item_movie_review, parent, false);

        ViewHolder vh = new ViewHolder(movieCell);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.review = reviews.get(position);

        holder.bind();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
