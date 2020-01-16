package com.example.periodischclient.Rest;

import com.example.periodischclient.Gps.CostumLocation;

import java.util.ArrayList;
import java.util.List;

public class Datensatz {
    public List<CostumLocation> getDatensatz() {
        return datensatz;
    }

    public void addLocation(CostumLocation loc) {
        this.datensatz.add(loc);
    }

    private List<CostumLocation> datensatz;

    public Datensatz(){
        datensatz = new ArrayList<>();
    }
}
