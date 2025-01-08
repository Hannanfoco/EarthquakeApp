package com.example.task;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EarthquakeApiService {
    @GET("query")
    Call<EarthquakeResponse> getEarthquakeData(
            @Query("format") String format,
            @Query("starttime") String startTime,
            @Query("endtime") String endTime
    );
}
