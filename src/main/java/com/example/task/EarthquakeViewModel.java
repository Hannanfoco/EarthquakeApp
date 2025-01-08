package com.example.task;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class EarthquakeViewModel extends ViewModel {
    private final LiveData<List<Earthquake>> earthquakeList;

    public EarthquakeViewModel() {
        EarthquakeRepository repository = new EarthquakeRepository();
        earthquakeList = repository.getData();
    }

    public LiveData<List<Earthquake>> getEarthquakes() {
        return earthquakeList;
    }
}
