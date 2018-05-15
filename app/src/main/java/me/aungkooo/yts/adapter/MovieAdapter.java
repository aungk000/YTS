package me.aungkooo.yts.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.aungkooo.yts.R;
import me.aungkooo.yts.viewholder.MovieViewHolder;
import me.aungkooo.yts.model.Movie;


public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>
{
    private Context context;
    private ArrayList<Movie> movieList;

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.movie, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position)
    {
        Movie movie = movieList.get(holder.getAdapterPosition());

        holder.txtTitle.setText(movie.getTitle());
        holder.txtYear.setText(String.valueOf(movie.getYear()));

        Picasso.with(context).load(movie.getMediumCoverImage()).into(holder.imgCover);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(ArrayList<Movie> movieList)
    {
        this.movieList.clear();
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }
}
