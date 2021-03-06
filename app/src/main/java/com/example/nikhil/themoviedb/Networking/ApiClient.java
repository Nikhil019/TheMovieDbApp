package com.example.nikhil.themoviedb.Networking;

import com.example.nikhil.themoviedb.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shubham on 7/15/2018.
 */

public class ApiClient {

        private static Retrofit retrofit;

        private static MovieDBService service;

       public static Retrofit getInstance(){
            if (retrofit == null) {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());
                retrofit = builder.build();
            }
            return retrofit;
        }

        public static MovieDBService getMovieDBService(){
            if(service == null){
                service = getInstance().create(MovieDBService.class);
            }
            return service;
        }
    }

