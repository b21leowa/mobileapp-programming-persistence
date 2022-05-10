package com.example.persistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private EditText brand;
    private EditText model;
    private EditText topspeed;
    private Button submitFields;
    private Button getFields;
    private ArrayList<Car> carsList;
    private TextView carView;




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
        getFields = findViewById(R.id.button_read);
        carView = findViewById(R.id.car);


        submitFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCars(brand.getText().toString(), model.getText().toString(), Integer.parseInt(topspeed.getText().toString()));
            }
        });

        getFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carsList = getCars();
                for (Car car: carsList) {
                    carView.append(car.getCarBrand() + " " + car.getCarModel() + " with top speed of " + car.getTopspeedString() + " km/h \n");
                }
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

    private ArrayList<Car> getCars() {
        Cursor cursor = database.query(DatabaseTables.Cars.TABLE_NAME, null, null, null,null,null,null);
        ArrayList<Car> cars = new ArrayList<>();
        while (cursor.moveToNext()) {
            Car car = new Car(
                    cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_BRAND)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_MODEL)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseTables.Cars.COLUMN_NAME_TOPSPEED))
            );
            cars.add(car);
        }
        cursor.close();
        return cars;
    }
}
