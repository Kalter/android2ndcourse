package com.itis.androidlab.fragmentsaffiche.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cinema {
    private String name;
    private String address;
    private List<Long> films;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Long> getFilms() {
        return films;
    }

    public void setFilms(List<Long> films) {
        this.films = films;
    }

    public static class CinemasArray {
        private List<Cinema> items;

        public List<Cinema> getItems() {
            return items;
        }

        public void setItems(List<Cinema> items) {
            this.items = items;
        }
    }
}
