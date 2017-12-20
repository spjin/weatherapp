package hu.ait.android.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import hu.ait.android.weatherapp.data.City;
import hu.ait.android.weatherapp.data.Weather;
import hu.ait.android.weatherapp.data.WeatherResult;
import hu.ait.android.weatherapp.network.WeatherAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    private String cityName;

    private ImageView ivIcon;
    private TextView tvCity;
    private TextView tvDesc;
    private TextView tvTemp;
    private TextView tvTempMinMax;
    private TextView tvSun;
    private TextView tvWind;
    private TextView tvHumidity;


    private String units = "metric";
    private final String API_KEY = "0064423748e649e3c492e97fb81137f7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        if(getIntent().hasExtra(CitiesActivity.KEY_CITY_NAME)){
            cityName = getIntent().getStringExtra(CitiesActivity.KEY_CITY_NAME);
        }

        ivIcon = (ImageView) findViewById(R.id.ivIcon);
        tvCity = (TextView) findViewById(R.id.tvCity);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
        tvTemp = (TextView) findViewById(R.id.tvTemp);
        tvTempMinMax = (TextView) findViewById(R.id.tvTempMinMax);
        tvSun = (TextView) findViewById(R.id.tvSun);
        tvWind = (TextView) findViewById(R.id.tvWind);
        tvHumidity = (TextView) findViewById(R.id.tvHumidity);

        setUp();
    }

    public void showTheWeather(Response<WeatherResult> response){

        tvCity.setText(response.body().getName());

        Glide.with(this).load(getString(R.string.api)
                + response.body().getWeather().get(0).getIcon() + getString(R.string.png)).into(ivIcon);

        tvDesc.setText("" + getString(R.string.weather_today) + " " + response.body().getWeather().get(0).getDescription());

        tvTemp.setText("" + getString(R.string.currentTemp) + " " + response.body().getMain().getTemp());

        tvTempMinMax.setText("" + getString(R.string.low_today) + " " + response.body().getMain().getTempMin()
                + " " + getString(R.string.high_today) +" " + response.body().getMain().getTempMax() + " " + getString(R.string.degC));

        tvSun.setText("" + getString(R.string.sunrise) + " " + response.body().getSys().getSunrise()
                + getString(R.string.sunset) + " " + response.body().getSys().getSunset());
        tvWind.setText("" + getString(R.string.wind_is) + " "+ response.body().getWind().getSpeed() + getString(R.string.ms)
                + " " + getString(R.string.and) + " " + response.body().getWind().getDeg() + " " +  getString(R.string.degrees));
        tvHumidity.setText(""+ getString(R.string.humidity_today) + " " + response.body().getMain().getHumidity() + getString(R.string.perc));
    }

    public void setUp(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

        Call<WeatherResult> call =
                weatherAPI.getWeather(cityName, units, API_KEY);

        call.enqueue(new Callback<WeatherResult>(){
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response){
                showTheWeather(response);
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t){
                Toast.makeText(WeatherActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
