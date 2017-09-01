package com.example.marisanity.ghibliapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.marisanity.ghibliapp.model.Film;
import com.example.marisanity.ghibliapp.service.GhibliApiService;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    GhibliApiService apiService;
    private String type = "films";
    private Realm realm;
    private List<Film> films = new ArrayList<>();
    private RecyclerView recyclerView;
    private FilmAdapter adapter;
    private SwipeRefreshLayout swiper;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swiper = (SwipeRefreshLayout) findViewById(R.id.swiper) ;

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updatePageContent();
                swiper.setRefreshing(false);
            }
        });

        apiService = GhibliApplication.getInstance().getService();


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FilmAdapter();
        recyclerView.setAdapter(adapter);

    }

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");

        realm = Realm.getDefaultInstance();

        films = realm.where(Film.class).findAll();


        if(films == null || films.size() == 0) {

            Log.d(TAG, "REALM IS NULL, LOADING FILMS FROM PAGE");
            loadPage();

        } else {

            Log.d(TAG, "LOADING FROM DATABASE");
            for(Film film : films) {
                Log.d(TAG, film.toString());

            }
        }

        adapter.initialize(realm.where(Film.class).findAll());

    }

    @Override
    protected void onPause() {
        super.onPause();


            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
            super.onPause();

    }

    //----------------------------------------------------------------------------------------------

    public void loadPage() {

        apiService.getPage(type).enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if(response != null) {

                    films = response.body();

                    realm.beginTransaction();
                    realm.copyToRealm(films);
                    realm.commitTransaction();

                    Log.d(TAG, "Films added to database: ");
                    for(Film film : films) {
                        Log.d(TAG, film.getTitle());
                    }

                } else {
                    Log.d(TAG, "response is null");
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void updatePageContent() {

        realm.beginTransaction();
        realm.delete(Film.class);
        realm.commitTransaction();

        loadPage();
    }

}




