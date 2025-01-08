package com.example.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Date;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.ViewHolder> {
    private final Context context;
    private List<Earthquake> earthquakeList;


    public EarthquakeAdapter(Context context, List<Earthquake> earthquakeList) {
        this.context = context;
        this.earthquakeList = earthquakeList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item (item_earthquake.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.item_earthquake, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EarthquakeAdapter.ViewHolder holder, int position) {
        Earthquake earthquake = earthquakeList.get(position);
        holder.titleTextView.setText(earthquake.getTitle());
        holder.placeTextView.setText(earthquake.getPlace());
        holder.magnitudeTextView.setText(String.valueOf(earthquake.getMagnitude()));


        double mag = earthquake.getMagnitude();
        int colorRes;
        if (mag < 2.0) {
            colorRes = R.color.magnitude_low;
        } else if (mag < 5.0) {
            colorRes = R.color.magnitude_medium;
        } else {
            colorRes = R.color.magnitude_high;
        }

        holder.magnitudeTextView.setTextColor(
                holder.itemView.getContext().getResources().getColor(colorRes)
        );


        String formattedDate = convertMillisToDate(earthquake.getTime());
        holder.timeTextView.setText(formattedDate);


        holder.typeTextView.setText(earthquake.getType());


        holder.itemView.setOnClickListener(v -> {
            String url = earthquake.getUrl();
            if (url != null && !url.isEmpty()) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                holder.itemView.getContext().startActivity(browserIntent);
            } else {
                Toast.makeText(holder.itemView.getContext(),
                        "URL not available",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return earthquakeList == null ? 0 : earthquakeList.size();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void updateEarthquakeList(List<Earthquake> newEarthquakeList) {
        this.earthquakeList = newEarthquakeList;
        notifyDataSetChanged();
    }


    private String convertMillisToDate(long millis) {
        Date date = new Date(millis);
        return DateFormat.format("MM/dd/yyyy", date).toString();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, placeTextView, magnitudeTextView, timeTextView, typeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            placeTextView = itemView.findViewById(R.id.placeTextView);
            magnitudeTextView = itemView.findViewById(R.id.magnitudeTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
        }
    }
}
