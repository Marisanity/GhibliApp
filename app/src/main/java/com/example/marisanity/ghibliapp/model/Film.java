package com.example.marisanity.ghibliapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Marisanity on 8/28/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Film extends RealmObject {

    @PrimaryKey
    private String id;
    private String title;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private String rt_score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRt_score() {
        return rt_score;
    }

    public void setRt_score(String rt_score) {
        this.rt_score = rt_score;
    }

    @Override
    public String toString() {
        return  "Title: " + title +
                "\nDescription: " + description +
                "\nDirector: " + director +
                "\nProducer: " + producer +
                "\nRelease_date: " + release_date +
                "\nRotten Tomatoes score: " + rt_score;
    }
}
