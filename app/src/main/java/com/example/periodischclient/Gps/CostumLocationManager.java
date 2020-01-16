package com.example.periodischclient.Gps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.periodischclient.BerichtigungsManager;
import com.example.periodischclient.Rest.RestManager;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.content.Context.LOCATION_SERVICE;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class CostumLocationManager {
    public static LocationManager locManager;
    public static TextView ausgabe;
    private static CostumLocation result;
    static AppCompatActivity activityG;
    public static CostumLocation costumLastLocation(AppCompatActivity activity){
        activityG = activity;
        BerichtigungsManager.isLocationGranted(activity);
        result = null;
        getFusedLocationProviderClient(activity).getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    result = mapToCostum(location);
                    float speed = location.getSpeed();
                    RestManager.postLocation(result);
                    Toast.makeText(activity.getApplicationContext(), "Location : "+  location.getLatitude()+"\n"+location.getLongitude(),Toast.LENGTH_LONG);
                }
                Log.e("pos", "onSuccess: Location : "+  location.getLatitude()+"\n"+location.getLongitude());
            }
        });
        return result;
    }

    public static CostumLocation mapToCostum(Location location) {
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

    public static void updateLocationPeriod(AppCompatActivity activity, long periode){
        locManager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);
        if (CostumLocationManager.locManager.isLocationEnabled()) {
                if (activity.getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && activity.getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }else {
                    CostumLocationManager.locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, periode, 0, new CostumLocationListener(), Looper.myLooper());
                    Toast.makeText(activity,"ein Location sent",Toast.LENGTH_SHORT);
                }
        }
    }

    public static class CostumLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            RestManager.postLocation(CostumLocationManager.mapToCostum(location));
            Log.e("location post",location.getAltitude() +location.getLongitude()+"");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }


}
