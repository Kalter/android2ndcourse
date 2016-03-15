package com.itis.androidlab.database.network;


import com.itis.androidlab.database.models.FullWeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherRest {

    @GET("/data/2.5/weather")
    Call<FullWeatherInfo> getTemperatureByCity(@Query("q") String cityName);




}
