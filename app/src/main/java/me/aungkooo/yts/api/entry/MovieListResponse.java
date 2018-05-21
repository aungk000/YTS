package me.aungkooo.yts.api.entry;

import com.google.gson.annotations.SerializedName;


public class MovieListResponse
{
    @SerializedName("status")
    private String status;
    @SerializedName("status_message")
    private String statusMessage;
    @SerializedName("data")
    private MovieList data;

    public MovieListResponse(String status, String statusMessage, MovieList data) {
        this.status = status;
        this.statusMessage = statusMessage;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public MovieList getData() {
        return data;
    }
}
