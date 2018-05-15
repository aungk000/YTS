package me.aungkooo.yts.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.aungkooo.yts.R;


public class MovieViewHolder extends RecyclerView.ViewHolder
{
    public TextView txtTitle, txtYear;
    public View view;
    public ImageView imgCover;

    public MovieViewHolder(View itemView)
    {
        super(itemView);
        view = itemView;
        txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
        txtYear = (TextView) itemView.findViewById(R.id.txt_year);
        imgCover = (ImageView) itemView.findViewById(R.id.img_cover_small);
    }
}
