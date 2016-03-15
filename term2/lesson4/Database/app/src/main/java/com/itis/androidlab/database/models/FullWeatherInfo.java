package com.itis.androidlab.database.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FullWeatherInfo {

    @JsonProperty("coord")
    private Coordinates coordinates;

    private List<Weather> weather;

    private String base;

    @JsonProperty("main")
    private Temperature temperature;

    @JsonProperty("dt")
    private long date;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FullWeatherInfo{" +
                "coordinates=" + coordinates +
                ", weather=" + weather +
                ", base='" + base + '\'' +
                ", temperature=" + temperature +
                ", date=" + date +
                '}';
    }
}
