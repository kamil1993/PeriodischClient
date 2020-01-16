package com.example.periodischclient;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.periodischclient.Rest.ReqRes.TrackidReq;
import com.example.periodischclient.Rest.RestManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MainActivity extends AppCompatActivity {

    Button startBtn;
    TextView periodenEingabe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGui();
    }

    private void initGui(){
        startBtn = findViewById(R.id.startBtn);
        periodenEingabe = findViewById(R.id.periodenEingabe);
        TrackidReq req =  new TrackidReq();req.beschreibung="first Test aus Periodischen Client";req.name="kamils Test";req.teamid=25;


        startBtn.setOnClickListener(x->{
            int a = RestManager.trackidInit(this, req);
            int periode = Integer.parseInt(periodenEingabe.getText().toString());
            Toast.makeText(this.getApplicationContext(),"Die Periode ist "+periode,Toast.LENGTH_LONG).show();

        });
    }
}
