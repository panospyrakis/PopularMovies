package com.udacity.spyrakis.popularmovies.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.spyrakis.popularmovies.R;
import com.udacity.spyrakis.popularmovies.data.MoviesContract;
import com.udacity.spyrakis.popularmovies.services.OnItemClickListener;

/**
 * Created by pspyrakis on 13/5/18.
 */
public class CursorListAdapter extends CursorAdapter {
    private static final String LOG_TAG = CursorListAdapter.class.getSimpleName();
    private OnItemClickListener listener;

    public static class ViewHolder {
        final ImageView imageView;

        ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.movieImg);
        }

        void bind(final int movieId, final OnItemClickListener listener) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movieId);
                }
            });
        }
    }

    public CursorListAdapter(Context context, Cursor c, int flags,OnItemClickListener listener ) {
        super(context, c, flags);
        mContext = context;
        this.listener = listener;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int layoutId = R.layout.item_movie_list;

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();


        int imageIndex = cursor.getColumnIndex(MoviesContract.MovieEntry.COLUMN_ICON);
        String image = cursor.getString(imageIndex);
        Picasso.with(context).load(image).placeholder(R.drawable.placeholder).into(viewHolder.imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                //i don't have something to do
            }

            @Override
            public void onError() {
                Toast.makeText(mContext,
                        mContext.getResources().getString(R.string.picasso_fail),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        int idIndex = cursor.getColumnIndex(MoviesContract.MovieEntry.MOVIE_ID);
        int id = Integer.valueOf(cursor.getString(idIndex));

        viewHolder.bind(id, listener);

    }

}
