package hu.ait.android.weatherapp.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sarahjin on 11/22/17.
 */

public class City extends RealmObject {

    @PrimaryKey
    private String cityID;

    private String cityTitle;
    private String createDate;

    public City(){}

    public City(String cityTitle, String createDate) {
        this.cityTitle = cityTitle;
        this.createDate = createDate;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCityID() {
        return cityID;
    }
}
