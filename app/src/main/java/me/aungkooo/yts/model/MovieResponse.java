package me.aungkooo.yts.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class MovieResponse
{
    @SerializedName("movie_count")
    private String movieCount;
    @SerializedName("limit")
    private String limit;
    @SerializedName("page_number")
    private String pageNumber;
    @SerializedName("movies")
    private ArrayList<Movie> results;

    public MovieResponse(String movieCount, String limit, String pageNumber, ArrayList<Movie> results) {
        this.movieCount = movieCount;
        this.limit = limit;
        this.pageNumber = pageNumber;
        this.results = results;
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

    public ArrayList<Movie> getResults() {
        return results;
    }
}
