package com.example.ggresit;

public class Constants {
    public static final String DATABASE_NAME = "TripManagement.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "TRIP_TABLE";

    //table col - field name
    public static final String CL_ID = "ID";
    public static final String CL_NAME = "NAME";
    public static final String CL_DEST = "DEST";
    public static final String CL_DATE = "DATE";
    public static final String CL_RISK = "RISK";
    public static final String CL_DESC = "DESC";

    //query for create table
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
                    + CL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CL_NAME + " TEXT, "
                    + CL_DEST + " TEXT, "
                    + CL_DATE + " TEXT, "
                    + CL_RISK + " TEXT, "
                    + CL_DESC + " TEXT"
                    + " );";



}
