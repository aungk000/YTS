package me.aungkooo.yts.api.entry;

import com.google.gson.annotations.SerializedName;


public class MovieResponse
{
    @SerializedName("status")
    private String status;
    @SerializedName("status_message")
    private String statusMessage;
    @SerializedName("data")
    private Movie data;

    public MovieResponse(String status, String statusMessage, Movie data) {
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

    public Movie getData() {
        return data;
    }
}
