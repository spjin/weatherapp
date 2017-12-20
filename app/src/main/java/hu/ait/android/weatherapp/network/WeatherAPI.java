package hu.ait.android.weatherapp.network;

/**
 * Created by sarahjin on 11/23/17.
 */

import hu.ait.android.weatherapp.data.WeatherResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {
    @GET("data/2.5/weather")
    Call<WeatherResult> getWeather(@Query("q") String name,
                                   @Query("units") String unitType,
                                   @Query("appid") String appId);

}