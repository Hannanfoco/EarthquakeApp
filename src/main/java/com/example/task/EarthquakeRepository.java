package com.example.task;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeRepository {
    private final EarthquakeApiService apiService;

    public EarthquakeRepository() {
        apiService = Client.getClient().create(EarthquakeApiService.class);
    }

    public LiveData<List<Earthquake>> getData() {
        MutableLiveData<List<Earthquake>> data = new MutableLiveData<>();

        Call<EarthquakeResponse> call = apiService.getEarthquakeData("geojson", "2023-01-01", "2023-01-02");
        call.enqueue(new Callback<EarthquakeResponse>() {
            @Override
            public void onResponse(@NonNull Call<EarthquakeResponse> call, @NonNull Response<EarthquakeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Feature> features = response.body().getFeatures();
                    Log.d("EarthquakeRepository", "Number of features received: " + features.size());
                    List<Earthquake> propertiesList = new ArrayList<>();
                    for (Feature feature : features) {
                        propertiesList.add(feature.getProperties());
                    }
                    data.postValue(propertiesList);
                } else {
                    Log.e("EarthquakeRepository", "API error: " + response.errorBody());
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<EarthquakeResponse> call, @NonNull Throwable t) {
                Log.e("EarthquakeRepository", "Network error: " + t.getMessage());
                data.postValue(null);
            }
        });

        return data;
    }
}
