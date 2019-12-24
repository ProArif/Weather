package com.nodeers.app.weather.interfaces;

import com.nodeers.app.weather.responses.CurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {


    @GET("weather?")
    Call<CurrentWeatherResponse> getCurrentWeather(@Query("lat") String lat,
                                                   @Query("lon") String lon,
                                                   @Query("units") String units,
                                                   @Query("appid") String appid);

}
