package com.example.periodischclient.Gps;

import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.periodischclient.BerichtigungsManager;
import com.example.periodischclient.Rest.RestManager;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class CostumLocationManager {
    private static CostumLocation result;
    public static CostumLocation costumLastLocation(AppCompatActivity activity){
        BerichtigungsManager.isLocationGranted(activity);
        result = null;
        getFusedLocationProviderClient(activity).getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    result = mapToCostum(location);
                    RestManager.postLocation(activity,result);
                    Toast.makeText(activity.getApplicationContext(), "Location : "+  location.getLatitude()+"\n"+location.getLongitude(),Toast.LENGTH_LONG);
                }
                Log.e("pos", "onSuccess: Location : "+  location.getLatitude()+"\n"+location.getLongitude());
            }
        });
        return result;
    }

    private static CostumLocation mapToCostum(Location location) {
        CostumLocation l = new CostumLocation();
        l.altitude = new Double(location.getAltitude()).longValue();
        l.timestamp = location.getTime();
        l.latitude = location.getLatitude();
        l.longitude = location.getLongitude();
        l.teamid = 25;
        l.trackid = RestManager.trackid;
        l.session = "test Session from kamil";
        l.counter = RestManager.counter;
        return l;
    }
}
