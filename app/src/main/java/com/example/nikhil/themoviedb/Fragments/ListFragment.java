package com.example.nikhil.themoviedb.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.nikhil.themoviedb.Adapter.RowListener;
import com.example.nikhil.themoviedb.Adapter.ShowMovieAdapter;
import com.example.nikhil.themoviedb.Adapter.ShowsTvAdapter;
import com.example.nikhil.themoviedb.Constants;
import com.example.nikhil.themoviedb.Database.Database;
import com.example.nikhil.themoviedb.Database.MovieDAO;
import com.example.nikhil.themoviedb.Database.ShowDAO;
import com.example.nikhil.themoviedb.Activities.GridActivity;
import com.example.nikhil.themoviedb.ListLoadListener;
import com.example.nikhil.themoviedb.LoadList;
import com.example.nikhil.themoviedb.Networking.ApiClient;
import com.example.nikhil.themoviedb.R;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements ListLoadListener {
RecyclerView RV;
ProgressBar progressBar;
TextView tv;
Button btnViewAll;
List<Movie> movies=new ArrayList<>();
ShowMovieAdapter moviesAdapter;
List<Shows> shows=new ArrayList<>();
ShowsTvAdapter showsAdapter;
String type,fragment;
Bundle bundle;
LoadList listLoader;
int page=1;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output=inflater.inflate(R.layout.fragment_list, container, false);
        RV=output.findViewById(R.id.RecyclerView);
        progressBar=output.findViewById(R.id.progressBar);
        tv=output.findViewById(R.id.textView);
        btnViewAll=output.findViewById(R.id.button);
        RV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        bundle=getArguments();
        listLoader=new LoadList(bundle,getContext(),this);
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), GridActivity.class);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
        fragment=bundle.getString(Constants.FRAGMENT);
        type=bundle.getString(Constants.TYPE);
        if(fragment.equals(Constants.MOVIES_FRAGMENT))
        {
            movies.addAll(listLoader.getMovies(page));
            if(type.equals(Constants.NOW_SHOWING_MOVIES))
            {
                tv.setText("Now Showing");
            }
            else if(type.equals(Constants.POPULAR_MOVIES))
            {
                tv.setText("Popular");
            }
            else if(type.equals(Constants.TOP_RATED_MOVIES))
            {
               tv.setText("Top Rated");
            }
            else if(type.equals(Constants.UPCOMING_MOVIES))
            {
                tv.setText("Upcoming");
            }
            if(movies.size()>0)
            {
                RV.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
            moviesAdapter=new ShowMovieAdapter(getContext(), movies, new RowListener() {
                @Override
                public void onListItemClicked(View view, int position) {

                }
            });
            RV.setAdapter(moviesAdapter);
        }
        else if(fragment.equals(Constants.SHOWS_FRAGMENT))
        {
            shows.addAll(listLoader.getShows(page));
            if(type.equals(Constants.AIR_TODAY_SHOWS))
            {
               tv.setText("Airing Today");
            }
            else if(type.equals(Constants.POPULAR_SHOWS))
            {
                tv.setText("Popular");
            }
            else if(type.equals(Constants.TOP_RATED_SHOWS))
            {
               tv.setText("Top Rated");
            }
            else if(type.equals(Constants.ON_AIR_SHOWS))
            {
               tv.setText("On Air");
            }
            if(shows.size()>0)
            {
                RV.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
            showsAdapter=new ShowsTvAdapter(getContext(), shows, new RowListener() {
                @Override
                public void onListItemClicked(View view, int position) {

                }
            });
            RV.setAdapter(showsAdapter);
        }
        
        return output;
    }

    @Override
    public void onMoviesListLoaded(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        moviesAdapter.notifyDataSetChanged();
        if(movies.size()>0)
        {
            RV.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onShowsListLoaded(List<Shows> shows) {
        this.shows.clear();
        this.shows.addAll(shows);
        showsAdapter.notifyDataSetChanged();
        if(shows.size()>0)
        {
            RV.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
