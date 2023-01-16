package com.example.technopat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class remainder extends AppCompatActivity {

    private mySQLiteDBHandler dbHandler;
    private EditText editTxt1;
    private CalendarView calendarView;
    private String selectedDate;
    private SQLiteDatabase sqLiteDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder);

        editTxt1 = findViewById(R.id.editText2);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = Integer.toString(year) + month + dayOfMonth;
            ReadDatabase(view);
        });

        try{

            dbHandler = new mySQLiteDBHandler(this, "CalendarDatabase", null,1);
            sqLiteDatabase = dbHandler.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE EventCalendar(Date TEXT, Event TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertDatabase(View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Date",selectedDate);
        contentValues.put("Event", editTxt1.getText().toString());
        sqLiteDatabase.insert("EventCalendar", null, contentValues);

    }

    public void ReadDatabase(View view){
        String query = "Select Event from EventCalendar where Date = " + selectedDate;
        try{
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            cursor.moveToFirst();
            editTxt1.setText(cursor.getString(0));
        }
        catch (Exception e){
            e.printStackTrace();
            editTxt1.setText("");
        }
    }

}