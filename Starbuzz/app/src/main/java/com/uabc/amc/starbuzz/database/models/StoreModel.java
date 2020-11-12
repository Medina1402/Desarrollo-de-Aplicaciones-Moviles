package com.uabc.amc.starbuzz.database.models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.uabc.amc.starbuzz.R;

public class StoreModel {
    public static final String TABLE_NAME = "store";

    public static void OnCreateTableDataBase(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + TABLE_NAME + " (" +
                "_id integer primary key autoincrement," +
                "name text," +
                "service text," +
                "description text," +
                "image_resource_id integer," +
                "favorite integer" +
        ");";
        sqLiteDatabase.execSQL(query);
    }


    public static void insert(SQLiteDatabase sqLiteDatabase, String name, String service, String description, int resourceId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("service", service);
        contentValues.put("description", description);
        contentValues.put("image_resource_id", resourceId);
        contentValues.put("favorite", 0);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }


    public static void testValues(SQLiteDatabase sqLiteDatabase) {
        insert(sqLiteDatabase, "Tijuana (Otay)", "09:00 - 19:00",
                "We are in an ideal area to visit before traveling to the USA, we visit we have many promotions",
                R.drawable.map_otay
        );
        insert(sqLiteDatabase, "Tijuana (Alameda Otay)", "06:00 - 18:00",
                "If you visit Alameda Otay Square, you should visit us and try our delicious LATTE drink, it is one of the best in the city",
                R.drawable.map_alameda
        );
        insert(sqLiteDatabase, "Tijuana (Zona Rio)", "09:00 - 22:00",
                "Before we go to work, visit us, we have many promotions during morning hours",
                R.drawable.map_zonario
        );
    }
}