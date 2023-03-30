package com.example.ggresit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdapterTrip extends RecyclerView.Adapter<AdapterTrip.TripViewHolder> {

    public Context context;
    private ArrayList<ModelTrip> tripList;

    public AdapterTrip(Context context, ArrayList<ModelTrip> tripList) {
        this.context=context;
        this.tripList=tripList;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_trip_item,parent,false);
        TripViewHolder vh = new TripViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        ModelTrip modelTrip = tripList.get(position);
        //get data
        String id = modelTrip.getId();
        String name = modelTrip.getName();
        String dest = modelTrip.getDest();
        String date = modelTrip.getDate();
        String risk = modelTrip.getRisk();
//        String desc = modelTrip.getDesc();

        //set data in view
        holder.tripId.setText(id);
        holder.tripName.setText(name);
        holder.tripDest.setText(dest);
        holder.tripDate.setText(date);
        holder.tripRisk.setText(risk);
//        holder.tripDesc.setText(desc);


    }

    @Override
    public int getItemCount() { return tripList.size(); }

    static class TripViewHolder extends RecyclerView.ViewHolder{

        //view for row_trip_item
        TextView tripId,tripName,tripDest,tripDate,tripRisk,tripDesc;
        public TripViewHolder(@NonNull View itemView){
            super(itemView);
            tripId = itemView.findViewById(R.id.trip_id);
            tripName = itemView.findViewById(R.id.trip_name);
            tripDest = itemView.findViewById(R.id.trip_dest);
            tripDate = itemView.findViewById(R.id.trip_date);
            tripRisk = itemView.findViewById(R.id.trip_risk);
        }
    }
}
