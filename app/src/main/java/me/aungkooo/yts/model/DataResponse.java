package me.aungkooo.yts.model;

import com.google.gson.annotations.SerializedName;


public class DataResponse
{
    @SerializedName("status")
    private String status;
    @SerializedName("status_message")
    private String statusMessage;
    @SerializedName("data")
    private MovieResponse movieResponses;

    public DataResponse(String status, String statusMessage, MovieResponse movieResponses) {
        this.status = status;
        this.statusMessage = statusMessage;
        this.movieResponses = movieResponses;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public MovieResponse getMovieResponses() {
        return movieResponses;
    }
}
