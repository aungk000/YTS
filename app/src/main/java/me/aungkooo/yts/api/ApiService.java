package me.aungkooo.yts.api;

import me.aungkooo.yts.api.entry.MovieListResponse;
import me.aungkooo.yts.api.entry.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService
{
    @GET("list_movies.json?")
    Call<MovieListResponse> getMovieList(
            @Query("limit") int limit,
            @Query("page") int page,
            @Query("quality") String quality,
            @Query("minimum_rating") int minimumRating,
            @Query("genre") String genre,
            @Query("sort_by") String sortBy,
            @Query("order_by") String orderBy,
            @Query("with_rt_ratings") boolean withRtRatings
    );

    @GET("list_movies.json?")
    Call<MovieListResponse> getMovieList(
            @Query("sort_by") String sortBy,
            @Query("order_by") String orderBy
    );

    @GET("list_movies.json?")
    Call<MovieListResponse> getMovieList(
            @Query("quality") String quality
    );

    @GET("list_movies.json?")
    Call<MovieListResponse> searchMovieList(@Query("query_term") String queryTerm);

    @GET("movie_details.json?")
    Call<MovieResponse> getMovie(
            @Query("movie_id") int movieId,
            @Query("with_images") boolean withImages,
            @Query("with_cast") boolean withCast
    );
}
