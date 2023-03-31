package com.example.ggresit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText _name,_dest,_date,_risk,_desc ;
    private Button add_button;

    //String variable
    private String id,name,dest,date,risk,desc;
    private Boolean isEditMode;
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


        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //init view
        _name = findViewById(R.id._name);
        _date = findViewById(R.id._date);
        _dest = findViewById(R.id._dest);
        _risk = findViewById(R.id._risk);
        _desc = findViewById(R.id._desc);
        add_button = findViewById(R.id.add_button);

        //get intent data
        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode",false);

        if(isEditMode){
            //set title
            actionBar.setTitle("Update Trip");
            //get other value form intent
            id = intent.getStringExtra("ID");
            name = intent.getStringExtra("NAME");
            date = intent.getStringExtra("DATE");
            dest = intent.getStringExtra("DEST");
            risk = intent.getStringExtra("RISK");
            //ser value in editText field
            _name.setText(name);
            _date.setText(date);
            _dest.setText(dest);
            _risk.setText(risk);
        }else {
            //add mode on
            actionBar.setTitle("Add Trip");
        }

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
        date = _date.getText().toString();
        dest = _dest.getText().toString();
        risk = _risk.getText().toString();
        desc = _desc.getText().toString();

        //check edit or add mode to save data to database
        //check filed data
        if (!name.isEmpty() &&!dest.isEmpty() &&!date.isEmpty() &&!risk.isEmpty() &&!desc.isEmpty()){
            //save data
            if(isEditMode){
                //edit mode
                dbHelper.updateTrip(
                        ""+id,
                        ""+name,
                        ""+date,
                        ""+dest,
                        ""+risk,
                        ""+desc
                );

                Toast.makeText(getApplicationContext(), "Update Successfully! "+id, Toast.LENGTH_SHORT).show();

            }else{
                //add mode
                long id = dbHelper.insertTrip(
                        ""+name,
                        ""+date,
                        ""+dest,
                        ""+risk,
                        ""+desc
                );
                //check insert data successfully
                displayNextAlert(name, date, dest, risk, desc);
                Toast.makeText(getApplicationContext(), "Add Successfully! "+id, Toast.LENGTH_SHORT).show();
            }


        }else{
            //show toast
            Toast.makeText(getApplicationContext(), "Please enter all the boxes!", Toast.LENGTH_SHORT).show();
        }
    }


    //back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void displayNextAlert(String name, String dest, String date, String risk, String desc) {
        new AlertDialog.Builder(this).setTitle("Add New Trip")
                .setMessage(
                        "Trip name: " + name +
                                "\n Destination: " + dest +
                                "\n Date: " + date +
                                "\n Risk Assessment: " + risk +
                                "\n Description: " + desc
                )
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();
    }
}