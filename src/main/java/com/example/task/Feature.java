package com.example.task;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class Feature {
    @SerializedName("properties")
    private Earthquake properties;

}