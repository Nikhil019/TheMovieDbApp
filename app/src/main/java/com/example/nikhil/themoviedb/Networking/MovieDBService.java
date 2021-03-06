package com.example.nikhil.themoviedb.Networking;

import com.example.nikhil.themoviedb.entities.Movies.NowShowingMovie;
import com.example.nikhil.themoviedb.entities.Movies.PopularMovie;
import com.example.nikhil.themoviedb.entities.Movies.TopRatedMovie;
import com.example.nikhil.themoviedb.entities.Movies.UpcomingMovie;
import com.example.nikhil.themoviedb.entities.TvShows.AirTodayShows;
import com.example.nikhil.themoviedb.entities.TvShows.OnAirShows;
import com.example.nikhil.themoviedb.entities.TvShows.PopularShows;
import com.example.nikhil.themoviedb.entities.TvShows.TopRatedShows;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shubham on 7/15/2018.
 */

public interface MovieDBService {
    @GET("movie/upcoming")
    Call<UpcomingMovie> getUpComingMovies(@Query("api_key") String key,@Query("page") int page);
    @GET("movie/now_playing")
    Call<NowShowingMovie> getNowShowingMovies(@Query("api_key") String key,@Query("page") int page);
    @GET("movie/top_rated")
    Call<TopRatedMovie> getTopRatedMovies(@Query("api_key") String key,@Query("page") int page);
    @GET("movie/popular")
    Call<PopularMovie> getPopularMovies(@Query("api_key") String key,@Query("page") int page);
    @GET("tv/on_the_air")
    Call<OnAirShows> getOnAirShows(@Query("api_key") String key,@Query("page") int page);
    @GET("tv/airing_today")
    Call<AirTodayShows> getAirTodayShows(@Query("api_key") String key,@Query("page") int page);
    @GET("tv/top_rated")
    Call<TopRatedShows> getTopRatedShows(@Query("api_key") String key,@Query("page") int page);
    @GET("tv/popular")
    Call<PopularShows> getPopularShows(@Query("api_key") String key,@Query("page") int page);
}
