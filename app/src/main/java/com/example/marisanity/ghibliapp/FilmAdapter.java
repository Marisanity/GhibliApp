package com.example.marisanity.ghibliapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marisanity.ghibliapp.databinding.ListFilmBinding;
import com.example.marisanity.ghibliapp.model.Film;
import com.example.marisanity.ghibliapp.view.RecyclerView;
import com.example.marisanity.ghibliapp.viewholder.FilmViewHolder;
import com.example.marisanity.ghibliapp.viewholder.LoadingViewHolder;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Marisanity on 8/29/2017.
 */

public class FilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RealmChangeListener<RealmResults<Film>> {

    private static final int KEY_POSITION_LOADING = 0;
    private static final int KEY_POSITION_ITEM = 1;

    private RealmResults<Film> films;
    private boolean isLoading = false;


    public void initialize(RealmResults<Film> films) {
        this.films = films;
        films.addChangeListener(this);
        notifyDataSetChanged();
    }


    @Override
    public void onChange(RealmResults<Film> films) {
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == KEY_POSITION_ITEM) {
            View view = inflater.inflate(R.layout.list_film, parent, false);
            ListFilmBinding binding = ListFilmBinding.inflate(inflater, parent, false);
            return new FilmViewHolder(binding);

        } else if (viewType == KEY_POSITION_LOADING) {
            View view = inflater.inflate(R.layout.list_item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FilmViewHolder) {
            Film film = films.get(position);


            ((FilmViewHolder) holder).binding.setItem(film);

        }

    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);


    }

    public void setIsLoading(boolean currentlyLoading){
        isLoading = currentlyLoading;
        notifyDataSetChanged();
    }

    @Override
        public int getItemCount() {

        int count = 0;

        if(films != null || films.size() > 0){
            count += films.size();
        }

        if(isLoading) count++;

        return count;
        }


    @Override
    public int getItemViewType(int position) {

        if(isLoading){
            if(films != null || films.size() > position){
                return KEY_POSITION_LOADING;
            }
        }

        return KEY_POSITION_ITEM;
    }
}

