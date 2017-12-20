package hu.ait.android.weatherapp;

import android.app.Application;

import hu.ait.android.weatherapp.network.WeatherAPI;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sarahjin on 11/22/17.
 */


public class WeatherApplication extends Application {

    private Realm realmCity;

    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);

    }

    public void openRealm() {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realmCity = Realm.getInstance(config);
    }

    public void closeRealm() {
        realmCity.close();
    }

    public Realm getRealmCities() {
        return realmCity;
    }
}
