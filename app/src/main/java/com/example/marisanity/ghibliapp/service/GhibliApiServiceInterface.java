package com.example.marisanity.ghibliapp.service;

import com.example.marisanity.ghibliapp.model.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Marisanity on 8/28/2017.
 */

public interface GhibliApiServiceInterface {

        @GET("{type}")
        Call<List<Film>> getFilms(@Path("type") String type);


}
