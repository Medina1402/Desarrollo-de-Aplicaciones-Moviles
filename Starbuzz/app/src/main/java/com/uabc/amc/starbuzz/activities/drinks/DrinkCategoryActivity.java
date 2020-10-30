package com.uabc.amc.starbuzz.activities.drinks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.uabc.amc.starbuzz.R;
import com.uabc.amc.starbuzz.database.StarbuzzDatabaseHelper;
import com.uabc.amc.starbuzz.database.models.DrinkModel;

public class DrinkCategoryActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_category);
        setTitle(R.string.title_category_activity_drink);

        ListView listDrinks = findViewById(R.id.list_drink);
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);

        try {
            database = starbuzzDatabaseHelper.getReadableDatabase();
            cursor = database.query(
                    DrinkModel.TABLE_NAME,
                    new String[]{"_id", "name"},
                    null, null, null, null, null
            );

            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"name"},
                    new int[]{android.R.id.text1},
                    0
            );

            listDrinks.setAdapter(simpleCursorAdapter);
        } catch (SQLiteException ignore) {
            Toast toast = Toast.makeText(this, "Drink Category Activity - Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        /*
         * Change item Listener
         */
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DrinkCategoryActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINK_ID, (int) l);
                startActivity(intent);
            }
        };

        listDrinks.setOnItemClickListener(itemClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        database.close();
    }
}