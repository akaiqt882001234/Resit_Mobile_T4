package com.example.ggresit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText _name,_dest,_date,_risk,_desc ;
    private Button add_button;

    //String variable
    private String name,dest,date,risk,desc;
    //action bar
    private ActionBar actionBar;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //init db
        dbHelper = new DBHelper(this);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Add Trip");

        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //init view
        _name = findViewById(R.id._name);
        _dest = findViewById(R.id._dest);
        _date = findViewById(R.id._date);
        _risk = findViewById(R.id._risk);
        _desc = findViewById(R.id._desc);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

    }

    private void saveData() {
        //take user giver data in variable
        name = _name.getText().toString();
        dest = _name.getText().toString();
        date = _date.getText().toString();
        risk = _risk.getText().toString();
        desc = _desc.getText().toString();

        //check filed data
        if (!name.isEmpty() ||!dest.isEmpty() ||!date.isEmpty() ||!risk.isEmpty() ||!desc.isEmpty()){
            //save data
            long id = dbHelper.insertTrip(
                    ""+name,
                    ""+dest,
                    ""+date,
                    ""+risk,
                    ""+desc
            );
            //check insert data successfully
            Toast.makeText(getApplicationContext(), "Add Success! "+id, Toast.LENGTH_SHORT).show();

        }else{
            //show toast
            Toast.makeText(getApplicationContext(), "No data to save...", Toast.LENGTH_SHORT).show();
        }
    }


    //back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}