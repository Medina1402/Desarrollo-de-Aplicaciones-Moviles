package com.uabc.amc.starbuzz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.uabc.amc.starbuzz.database.models.DrinkModel;
import com.uabc.amc.starbuzz.database.models.FoodModel;
import com.uabc.amc.starbuzz.database.models.StoreModel;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_starbuzz";
    private static final int DB_VERSION = 1;

    public StarbuzzDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        DrinkModel.OnCreateTableDataBase(sqLiteDatabase);
        FoodModel.OnCreateTableDataBase(sqLiteDatabase);
        StoreModel.OnCreateTableDataBase(sqLiteDatabase);

        // Datos por defecto
        DrinkModel.testValues(sqLiteDatabase);
        FoodModel.testValues(sqLiteDatabase);
        StoreModel.testValues(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Actualizar la DB - Por ahora no se usara
    }
}
