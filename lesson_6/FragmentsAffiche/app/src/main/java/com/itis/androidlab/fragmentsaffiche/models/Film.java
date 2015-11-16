package com.itis.androidlab.fragmentsaffiche.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {
    private String title;
    private String director;
    private String description;
    @JsonProperty("premier_date")
    private String date;

    public Film() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FilmArray {
        public ArrayList<Film> items;

        public ArrayList<Film> getItems() {
            return items;
        }

        public void setItems(ArrayList<Film> items) {
            this.items = items;
        }
    }
}
