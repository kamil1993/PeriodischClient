package com.example.periodischclient;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.periodischclient.Gps.CostumLocation;
import com.example.periodischclient.Gps.CostumLocationManager;
import com.example.periodischclient.Gps.LocationAsync;
import com.example.periodischclient.Rest.ReqRes.TrackidReq;
import com.example.periodischclient.Rest.RestManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MainActivity extends AppCompatActivity {

    Button startBtn;
    TextView periodenEingabe;
    private FusedLocationProviderClient fusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        //CostumLocation b = CostumLocationManager.costumLastLocation(this);
        RestManager.trackidInit(this, "first Test aus Periodischen Client","fuckTest",25);
        initGui();
    }

    private void initGui(){
        startBtn = findViewById(R.id.startBtn);
        periodenEingabe = findViewById(R.id.periodenEingabe);
        startBtn.setOnClickListener(x->{

            //CostumLocation b = CostumLocationManager.costumLastLocation(this, this.fusedLocationClient);
            //Object[] param = {this};
            //new LocationAsync().execute(param);
            CostumLocationManager.costumLastLocation(this);
            //int periode = Integer.parseInt(periodenEingabe.getText().toString());
            //Toast.makeText(this.getApplicationContext(),"Die Periode ist "+periode,Toast.LENGTH_LONG).show();

        });
    }
}
