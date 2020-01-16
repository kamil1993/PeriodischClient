package com.example.periodischclient.Gps;

import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.periodischclient.BerichtigungsManager;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class CostumLocationManager {
    private static Location result;
    public static Location costumLastLocation(AppCompatActivity activity){
        BerichtigungsManager.isLocationGranted(activity);
        result = null;
        getFusedLocationProviderClient(activity).getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    result = location;
                    Toast.makeText(activity.getApplicationContext(), "Location : "+  location.getLatitude()+"\n"+location.getLongitude(),Toast.LENGTH_LONG);
                }
                Log.e("pos", "onSuccess: Location : "+  location.getLatitude()+"\n"+location.getLongitude());
            }
        });
        return result;
    }
}
