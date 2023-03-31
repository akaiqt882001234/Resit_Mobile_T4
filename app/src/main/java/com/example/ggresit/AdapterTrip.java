package com.example.ggresit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        String date = modelTrip.getDate();
        String dest = modelTrip.getDest();
        String risk = modelTrip.getRisk();
//        String desc = modelTrip.getDesc();

        //set data in view
        holder.tripId.setText(id);
        holder.tripName.setText(name);
        holder.tripDate.setText(date);
        holder.tripDest.setText(dest);
        holder.tripRisk.setText(risk);

        //handle edit btn click
        holder.tripEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,AddActivity.class);
                intent.putExtra("ID",id);
                intent.putExtra("NAME",name);
                intent.putExtra("DATE",date);
                intent.putExtra("DEST",dest);
                intent.putExtra("RISK",risk);
                intent.putExtra("isEditMode",true);
                context.startActivity(intent);

            }
        });
        // handle delete btn click
        holder.tripDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() { return tripList.size(); }

    static class TripViewHolder extends RecyclerView.ViewHolder{
        //view for row_trip_item
        TextView tripId,tripName,tripDest,tripDate,tripRisk,tripEdit,tripDelete;

        public TripViewHolder(@NonNull View itemView){
            super(itemView);
            tripId = itemView.findViewById(R.id.trip_id);
            tripName = itemView.findViewById(R.id.trip_name);
            tripDate = itemView.findViewById(R.id.trip_date);
            tripDest = itemView.findViewById(R.id.trip_dest);
            tripRisk = itemView.findViewById(R.id.trip_risk);
            tripEdit = itemView.findViewById(R.id.trip_edit);
            tripDelete = itemView.findViewById(R.id.trip_delete);
        }
    }
}
