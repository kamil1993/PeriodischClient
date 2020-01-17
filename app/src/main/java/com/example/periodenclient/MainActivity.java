package com.example.periodenclient;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.periodenclient.Gps.CostumLocationManager;
import com.example.periodenclient.Rest.RestManager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MainActivity extends AppCompatActivity {

    private LocationManager loc;
    Button startBtn;
    TextView periodenEingabe;
    TextView ausgabe;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BerichtigungsManager.isLocationGranted(this);
        RestManager.ausgabe = findViewById(R.id.ausgabe);
        CostumLocationManager.ausgabe = findViewById(R.id.ausgabe);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        RestManager.trackidInit(this, "first Test aus Periodischen Client", "Test", 25);
        initGui();
    }
    private void initGui(){
        startBtn = findViewById(R.id.startBtn);
        ausgabe = findViewById(R.id.ausgabe);
        ausgabe.setMovementMethod(new ScrollingMovementMethod());
        periodenEingabe = findViewById(R.id.periodenEingabe);
        startBtn.setOnClickListener(x->{
            Long periode = Long.parseLong(periodenEingabe.getText().toString());
            ausgabe.append("App je : "+periode+ "ms, holt GPS Fix\n");
            Toast.makeText(this.getApplicationContext(),"App je : "+periode+ "ms, holt GPS Fix",Toast.LENGTH_SHORT).show();
            CostumLocationManager.updateLocationPeriod(this,periode);
        });
    }
}
