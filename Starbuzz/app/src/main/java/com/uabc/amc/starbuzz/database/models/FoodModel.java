package com.uabc.amc.starbuzz.database.models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.uabc.amc.starbuzz.R;

public class FoodModel {
    public static final String TABLE_NAME = "food";

    public static void OnCreateTableDataBase(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + TABLE_NAME + " (" +
                "_id integer primary key autoincrement," +
                "name text," +
                "description text," +
                "image_resource_id integer," +
                "favorite integer" +
        ");";
        sqLiteDatabase.execSQL(query);
    }


    public static void insert(SQLiteDatabase sqLiteDatabase, String name, String description, int resourceId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("image_resource_id", resourceId);
        contentValues.put("favorite", 0);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }


    public static void testValues(SQLiteDatabase sqLiteDatabase) {
        insert(sqLiteDatabase, "Sandwich", "Enjoy the experience of fresh ingredients and the best meat in town", R.drawable.sandwich);
        insert(sqLiteDatabase, "Creeps", "A light dessert ideal to accompany with one of our drinks", R.drawable.crepas);
        insert(sqLiteDatabase, "Muffin", "The best of high pastries perfectly balanced with healthy ingredients", R.drawable.muffin);
    }
}