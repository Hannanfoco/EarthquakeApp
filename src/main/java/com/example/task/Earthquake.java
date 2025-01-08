package com.example.task;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Earthquake {
    @SerializedName("title")
    private String title;

    @SerializedName("place")
    private String place;

    @SerializedName("time")
    private long time;

    @SerializedName("mag")
    private double magnitude;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

}
