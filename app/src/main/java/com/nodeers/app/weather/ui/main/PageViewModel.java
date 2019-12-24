package com.nodeers.app.weather.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.nodeers.app.weather.helpers.ConstantClass;
import com.nodeers.app.weather.helpers.LocationFinder;
import com.nodeers.app.weather.interfaces.WeatherService;
import com.nodeers.app.weather.responses.CurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private WeatherService service;
    private LocationFinder locationFinder = new LocationFinder();
    private ConstantClass constantClass = new ConstantClass();

    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void getCurrentWeatherData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantClass.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(WeatherService.class);
        Call<CurrentWeatherResponse> call = service.getCurrentWeather(String.valueOf(locationFinder.getLat()),
                String.valueOf(locationFinder.getLng()),
                constantClass.units,
                constantClass.appid);

        call.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {

                if (response.code() == 200){

                    CurrentWeatherResponse weatherResponse = response.body();

                }
            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {

            }
        });


    }
}