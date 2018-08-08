package com.example.nikhil.themoviedb.Database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.nikhil.themoviedb.entities.Movies.Movie;
import com.example.nikhil.themoviedb.entities.Movies.NowShowingMovie;
import com.example.nikhil.themoviedb.entities.Movies.PopularMovie;
import com.example.nikhil.themoviedb.entities.Movies.TopRatedMovie;
import com.example.nikhil.themoviedb.entities.Movies.UpcomingMovie;
import com.example.nikhil.themoviedb.entities.TvShows.AirTodayShows;
import com.example.nikhil.themoviedb.entities.TvShows.OnAirShows;
import com.example.nikhil.themoviedb.entities.TvShows.PopularShows;
import com.example.nikhil.themoviedb.entities.TvShows.Shows;
import com.example.nikhil.themoviedb.entities.TvShows.TopRatedShows;

/**
 * Created by shubham on 7/17/2018.
 */
@android.arch.persistence.room.Database(entities = {Movie.class,UpcomingMovie.class,NowShowingMovie.class,TopRatedMovie.class, PopularMovie.class, Shows.class, TopRatedShows.class, PopularShows.class, AirTodayShows.class, OnAirShows.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase{
public abstract MovieDAO getMovieDAO();
public abstract ShowDAO getShowDAO();
private static Database database;
public static Database getInstance(Context context)
{
    if(database==null)
    {
        database= Room.databaseBuilder(context.getApplicationContext(),Database.class,"movie_db").allowMainThreadQueries().build();
    }
    return database;
}
}
