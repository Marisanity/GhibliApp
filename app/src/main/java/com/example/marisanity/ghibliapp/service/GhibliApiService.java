package com.example.marisanity.ghibliapp.service;

import com.example.marisanity.ghibliapp.model.Film;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


/**
 * Created by Marisanity on 8/28/2017.
 */

public class GhibliApiService {

    GhibliApiServiceInterface service;
    OkHttpClient okHttpClient;
    private static final String BASE_URL = "https://ghibliapi.herokuapp.com/";

    public GhibliApiService() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();

        service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(GhibliApiServiceInterface.class);
    }

    public Call<List<Film>> getPage(String type) {
        return service.getFilms(type);
    }

}
