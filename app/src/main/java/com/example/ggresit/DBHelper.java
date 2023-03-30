package com.example.ggresit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//class for database helper
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null,Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table on database
        db.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //drop table if exists
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        //create table again
        onCreate(db);
    }

    //Insert Function(add data to database)
    public long insertTrip( String name, String date, String dest, String risk, String desc){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(Constants.CL_NAME,name);
        cv.put(Constants.CL_DEST,dest);
        cv.put(Constants.CL_DATE,date);
        cv.put(Constants.CL_RISK,risk);
        cv.put(Constants.CL_DESC,desc);

        //insert data in row
        long id = db.insert(Constants.TABLE_NAME,null,cv);
        db.close();
        return id;
    }

    //get data
    public ArrayList<ModelTrip> getAllData(){
        //create arraylist
        ArrayList<ModelTrip> arrayList = new ArrayList<>();
        //sql command query
        String selectQuery = "SELECT * FROM "+Constants.TABLE_NAME;
        //get readable db
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        //looping through all record and add to list
        if(cursor.moveToFirst()){
            do {
                ModelTrip modelTrip = new ModelTrip(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.CL_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.CL_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.CL_DATE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.CL_DEST)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.CL_RISK)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.CL_DESC))

                );
                arrayList.add(modelTrip);
            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }
}
