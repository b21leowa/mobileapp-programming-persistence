package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private EditText brand;
    private EditText model;
    private EditText topspeed;
    private Button submitFields;
    private Byte nullValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        database = databaseHelper.getWritableDatabase();

        brand = findViewById(R.id.car_brand);
        model = findViewById(R.id.car_model);
        topspeed = findViewById(R.id.top_speed);
        submitFields = findViewById(R.id.button_write);

        submitFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCars(brand.getText().toString(), model.getText().toString(), Integer.parseInt(topspeed.getText().toString()));
            }
        });
    }

    private long addCars(String brand, String model, int topspeed){
        ContentValues values = new ContentValues();
        values.put(DatabaseTables.Cars.COLUMN_NAME_BRAND, brand);
        values.put(DatabaseTables.Cars.COLUMN_NAME_MODEL, model);
        values.put(DatabaseTables.Cars.COLUMN_NAME_TOPSPEED, topspeed);
        System.out.print(values);
        return database.insert(DatabaseTables.Cars.TABLE_NAME, null, values);
    }
}
