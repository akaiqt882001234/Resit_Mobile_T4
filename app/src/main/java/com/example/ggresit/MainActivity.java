package com.example.ggresit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton add_button;
    private RecyclerView tripRv;
    //db
    private DBHelper dbHelper;
    //adapter
    private AdapterTrip adapterTrip;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

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
                intent.putExtra("isEditMode",false);
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
        loadData(); //refresh data
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        //get search item
        MenuItem item = menu.findItem(R.id.searchTrip);
        //search area
        SearchView searchView = (SearchView) item.getActionView();
        //set max value of width
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchTrip(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchTrip(newText);
                return false;
            }
        });
        return true;
    }
    private void searchTrip(String query) {
        adapterTrip = new AdapterTrip(this,dbHelper.getSearchTrip(query));
        tripRv.setAdapter(adapterTrip);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteAllTrip:
                dbHelper.deleteAllTrip();
                onResume();
                break;
        }

        return true;
    }
}