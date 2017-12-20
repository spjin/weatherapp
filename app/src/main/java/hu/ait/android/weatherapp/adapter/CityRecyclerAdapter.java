package hu.ait.android.weatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import hu.ait.android.weatherapp.CitiesActivity;
import hu.ait.android.weatherapp.R;
import hu.ait.android.weatherapp.data.City;
import hu.ait.android.weatherapp.touch.CityTouchHelperAdapter;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by sarahjin on 11/22/17.
 */

public class CityRecyclerAdapter  extends RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder>
        implements CityTouchHelperAdapter {

    private List<City> cityList;
    private CitiesActivity context;
    private Realm realmCity;

    public CityRecyclerAdapter(CitiesActivity context, Realm realmCity){
        this.context = context;
        this.realmCity = realmCity;

        cityList = new ArrayList<City>();

        RealmResults<City> cityResult = realmCity.where(City.class).findAll().sort(context.getString(R.string.city_title), Sort.ASCENDING);

        for (City city : cityResult) {
            cityList.add(city);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cityRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.city_row, parent, false);

        return new ViewHolder(cityRow);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final City cityData = cityList.get(position);

        holder.tvCity.setText(cityData.getCityTitle());

        holder.btnDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((CitiesActivity)context).openWeatherActivity(
                        holder.getAdapterPosition(),
                        cityList.get(holder.getAdapterPosition()).getCityTitle()
                );
            }
        });

    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public void onItemDismiss(int position) {

        City cityToDelete = cityList.get(position);
        realmCity.beginTransaction();
        cityToDelete.deleteFromRealm();
        realmCity.commitTransaction();

        cityList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(cityList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(cityList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public void addCity(String cityTitle){
        realmCity.beginTransaction();

        City newCity = realmCity.createObject(City.class, UUID.randomUUID().toString());
        newCity.setCityTitle(cityTitle);
        newCity.setCreateDate(
                new Date(System.currentTimeMillis()).toString());

        realmCity.commitTransaction();

        cityList.add(0, newCity);
        
        notifyItemInserted(0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCity;
        private Button btnDetails;


        public ViewHolder(View itemView) {
            super(itemView);

            tvCity = itemView.findViewById(R.id.tvCity);
            btnDetails = itemView.findViewById(R.id.btnDetails);
        }
    }
    
}
