package com.uabc.amc.starbuzz.database.models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.uabc.amc.starbuzz.R;

public class DrinkModel {
    public static final String TABLE_NAME = "drink";

    public static void OnCreateTableDataBase(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + TABLE_NAME + " (" +
                "_id integer primary key autoincrement," +
                "name text," +
                "description text," +
                "image_resource_id integer" +
        ");";
        sqLiteDatabase.execSQL(query);
    }


    public static void insert(SQLiteDatabase sqLiteDatabase, String name, String description, int resourceId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("image_resource_id", resourceId);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }


    public static void testValues(SQLiteDatabase sqLiteDatabase) {
        insert(sqLiteDatabase, "Latte", "A couple of espresso shots with steamed milk", R.drawable.latte2);
        insert(sqLiteDatabase, "Cappuccino", "Espresso, hot milk, and steamed milk foam", R.drawable.cappuccino);
        insert(sqLiteDatabase, "Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter);
    }
}