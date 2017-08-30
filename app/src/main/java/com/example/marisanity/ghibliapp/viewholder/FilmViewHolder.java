package com.example.marisanity.ghibliapp.viewholder;

import com.example.marisanity.ghibliapp.databinding.ListFilmBinding;
import com.example.marisanity.ghibliapp.view.RecyclerView;

/**
 * Created by Marisanity on 8/29/2017.
 */

public class FilmViewHolder extends RecyclerView.ViewHolder {

    public ListFilmBinding binding;

    public FilmViewHolder(ListFilmBinding binding) {
        super(binding.getRoot());
        this.binding = binding;


    }



}


