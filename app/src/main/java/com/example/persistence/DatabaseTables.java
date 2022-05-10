package com.example.persistence;

public class DatabaseTables {
    static class Cars {
        static final String TABLE_NAME = "cars";
        static final String COLUMN_NAME_ID = "id";
        static final String COLUMN_NAME_BRAND = "brand";
        static final String COLUMN_NAME_MODEL = "model";
        static final String COLUMN_NAME_TOPSPEED = "topSpeed";
    }

    static final String SQL_CREATE_TABLE_CARS =
            // "CREATE TABLE cars (id INTEGER PRIMARY KEY, brand TEXT, model TEXT, topSpeed INT)"
                "CREATE TABLE " + Cars.TABLE_NAME + " (" +
                        Cars.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        Cars.COLUMN_NAME_BRAND + " TEXT," +
                        Cars.COLUMN_NAME_MODEL + " TEXT," +
                        Cars.COLUMN_NAME_TOPSPEED + " INTEGER)";

    static final String SQL_DELETE_TABLE_CARS =
            // "DROP TABLE IF EXISTS mountain"
            "DROP TABLE IF EXISTS " + Cars.TABLE_NAME;

}
