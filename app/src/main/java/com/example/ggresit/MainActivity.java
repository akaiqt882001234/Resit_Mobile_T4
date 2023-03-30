package com.example.ggresit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton add_button;
    private RecyclerView tripRv;

    //db
    private DBHelper dbHelper;

    //adapter
    private AdapterTrip adapterTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init db
        dbHelper = new DBHelper(MainActivity.this);

        //initialization
        add_button = findViewById(R.id.add_button);
        tripRv = findViewById(R.id.tripRv);
        tripRv.setHasFixedSize(true);

        //add listener
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        
        loadData();
    }

    public void loadData() {
        adapterTrip = new AdapterTrip(this,dbHelper.getAllData());
        tripRv.setAdapter(adapterTrip);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}