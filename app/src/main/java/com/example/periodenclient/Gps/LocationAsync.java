package com.example.periodenclient.Gps;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import com.example.periodenclient.Rest.RestManager;

public class LocationAsync extends AsyncTask<Object,Integer,CostumLocation> {
    CostumLocation loc;
    AppCompatActivity act;
    @Override
    protected CostumLocation doInBackground(Object[] params) {
        act = (AppCompatActivity) params[0];
        loc = CostumLocationManager.costumLastLocation(act);
        return loc;
    }
    @Override
    protected void onPostExecute(CostumLocation result) {
        super.onPostExecute(result);
        RestManager.postLocation(result);

    }
}
