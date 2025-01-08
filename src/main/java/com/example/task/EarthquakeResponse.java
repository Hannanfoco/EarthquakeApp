package com.example.task;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;


@Getter
public class EarthquakeResponse {
    @SerializedName("features")
    private List<Feature> features;

    public EarthquakeResponse(List<Feature> features) {
        this.features = features;
    }
}
