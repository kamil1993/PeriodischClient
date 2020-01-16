package com.example.periodischclient;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.periodischclient.Gps.CostumLocationManager;
import com.example.periodischclient.Rest.RestManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MainActivity extends AppCompatActivity {

    private LocationManager loc;
    Button startBtn;
    TextView periodenEingabe;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BerichtigungsManager.isLocationGranted(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        RestManager.trackidInit(this, "first Test aus Periodischen Client", "Test", 25);
        initGui();
    }
    private void initGui(){
        startBtn = findViewById(R.id.startBtn);
        periodenEingabe = findViewById(R.id.periodenEingabe);



        startBtn.setOnClickListener(x->{
            Long periode = Long.parseLong(periodenEingabe.getText().toString());
            Toast.makeText(this.getApplicationContext(),"App je : "+periode+ "ms, holt GPS Fix",Toast.LENGTH_SHORT).show();
            CostumLocationManager.updateLocationPeriod(this,periode);
        });
    }
}
