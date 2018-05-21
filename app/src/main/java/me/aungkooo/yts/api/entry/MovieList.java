package me.aungkooo.yts.api.entry;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class MovieList
{
    @SerializedName("movie_count")
    private String movieCount;
    @SerializedName("limit")
    private String limit;
    @SerializedName("page_number")
    private String pageNumber;
    @SerializedName("movies")
    private ArrayList<Movie> movies;

    public MovieList(String movieCount, String limit, String pageNumber, ArrayList<Movie> movies) {
        this.movieCount = movieCount;
        this.limit = limit;
        this.pageNumber = pageNumber;
        this.movies = movies;
    }

    public String getMovieCount() {
        return movieCount;
    }

    public String getLimit() {
        return limit;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}
