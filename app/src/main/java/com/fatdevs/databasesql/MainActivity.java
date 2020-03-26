package com.fatdevs.databasesql;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String Name = "Ram";
        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            // creates or opens a sql database
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR , age INT(3),id INTEGER PRIMARY KEY)");// executes database
            sqLiteDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Akshat',19)"); // adding the content
            sqLiteDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Ram',12)");
            sqLiteDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Carl',18)");
            @SuppressLint("Recycle") Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM Users", null);
            // DELETE FROM <TABLE-NAME> WHERE ---
            // WHERE age > 17 AND name LIKE '%a%' LIMIT 1
      //      sqLiteDatabase.execSQL("DELETE FROM users WHERE name LIKE '%a%' LIMIT 1");
            int IndexForName = c.getColumnIndex("name");
            int IndexForAge = c.getColumnIndex("age");
            int IndexforId = c.getColumnIndex("id");
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Log.i("Name:", c.getString(IndexForName));
                Log.i("Age:", c.getString(IndexForAge));
                Log.i("id:", c.getString(IndexforId));
                c.moveToNext(); // cursor moves further
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}