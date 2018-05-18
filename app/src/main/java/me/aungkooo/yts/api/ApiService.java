package me.aungkooo.yts.api;


import me.aungkooo.yts.model.DataResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService
{
    @GET("list_movies.json?limit=20")
    Call<DataResponse> getAllMovieList();

    @GET("list_movies.json?sort_by=download_count&order_by=desc&limit=10")
    Call<DataResponse> getPopularMovieList();

    @GET("list_movies.json?quality=3D&limit=20")
    Call<DataResponse> get3DMovieList();
}
