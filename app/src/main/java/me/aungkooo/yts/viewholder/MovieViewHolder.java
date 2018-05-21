package me.aungkooo.yts.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.aungkooo.yts.Base;
import me.aungkooo.yts.R;
import me.aungkooo.yts.api.entry.Movie;


public class MovieViewHolder extends Base.RecyclerViewHolder<Movie> implements View.OnClickListener
{
    @BindView(R.id.txt_title) TextView txtTitle;
    @BindView(R.id.txt_year) TextView txtYear;
    @BindView(R.id.img_cover_small) public ImageView imgCover;

    public MovieViewHolder(View itemView, Context context) {
        super(itemView, context);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onBind(ArrayList<Movie> itemList)
    {
        Movie movie = getItem(itemList);

        txtTitle.setText(movie.getTitle());
        txtYear.setText(String.valueOf(movie.getYear()));

        Picasso.with(getContext()).load(movie.getMediumCoverImage()).into(imgCover);
    }

    @Override
    public void onClick(View v) {

    }
}
