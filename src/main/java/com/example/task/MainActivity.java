package com.example.task;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EarthquakeAdapter earthquakeAdapter;
    private EarthquakeViewModel viewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        earthquakeAdapter = new EarthquakeAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(earthquakeAdapter);


        viewModel = new ViewModelProvider(this).get(EarthquakeViewModel.class);
        observeViewModel();
    }

    private void observeViewModel() {
        progressBar.setVisibility(View.VISIBLE);

        viewModel.getEarthquakes().observe(this, new Observer<List<Earthquake>>() {
            @Override
            public void onChanged(List<Earthquake> earthquakeList) {
                progressBar.setVisibility(View.GONE);
                if (earthquakeList != null) {
                    earthquakeAdapter.updateEarthquakeList(earthquakeList);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
