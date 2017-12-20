package hu.ait.android.weatherapp.data;

/**
 * Created by sarahjin on 11/24/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("type")
    @Expose
    public Double type;
    @SerializedName("id")
    @Expose
    public Double id;
    @SerializedName("message")
    @Expose
    public Double message;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("sunrise")
    @Expose
    public Double sunrise;
    @SerializedName("sunset")
    @Expose
    public Double sunset;

    public Double getType() {
        return type;
    }

    public Double getId() {
        return id;
    }

    public Double getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public Double getSunrise() {
        return sunrise;
    }

    public Double getSunset() {
        return sunset;
    }
}
