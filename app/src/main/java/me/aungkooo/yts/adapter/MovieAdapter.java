package me.aungkooo.yts.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.aungkooo.yts.Base;
import me.aungkooo.yts.R;
import me.aungkooo.yts.viewholder.MovieViewHolder;
import me.aungkooo.yts.model.Movie;


public class MovieAdapter extends Base.RecyclerAdapter<MovieViewHolder, Movie>
{

    public MovieAdapter(Context context) {
        super(context);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = createView(R.layout.movie, parent);

        return new MovieViewHolder(view, getContext());
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position)
    {
        holder.onBind(getItemList());

    }
}
