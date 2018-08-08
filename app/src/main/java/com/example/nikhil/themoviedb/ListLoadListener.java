package com.example.nikhil.themoviedb;

import com.example.nikhil.themoviedb.entities.Movies.Movie;
import com.example.nikhil.themoviedb.entities.TvShows.Shows;

import java.util.List;

/**
 * Created by shubham on 7/23/2018.
 */

public interface ListLoadListener {
    public void onMoviesListLoaded(List<Movie> movies);
    public void onShowsListLoaded(List<Shows> shows);
}
