package com.uabc.amc.starbuzz.activities.stores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uabc.amc.starbuzz.R;
import com.uabc.amc.starbuzz.database.StarbuzzDatabaseHelper;
import com.uabc.amc.starbuzz.database.models.FoodModel;
import com.uabc.amc.starbuzz.database.models.StoreModel;

import java.util.Objects;

public class StoreActivity extends AppCompatActivity {
    public static final String EXTRA_STORE_ID = "store_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        int storeId = (int) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_STORE_ID);
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);

        try {
            SQLiteDatabase database = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = database.query(
                    StoreModel.TABLE_NAME,
                    new String[]{"name, service, description, image_resource_id, favorite"},
                    "_id = ?",
                    new String[]{Integer.toString(storeId)},
                    null, null, null
            );

            if(cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String serviceText = cursor.getString(1);
                String descriptionText = cursor.getString(2);
                int photoId = cursor.getInt(3);
                boolean isFavorite = (cursor.getInt(4) > 0 );

                setTitle("Store - " + nameText);
                TextView name = findViewById(R.id.name);
                name.setText(nameText);

                TextView service = findViewById(R.id.service);
                service.setText(serviceText);

                TextView description = findViewById(R.id.description);
                description.setText(descriptionText);

                ImageView imageView = findViewById(R.id.photo);
                imageView.setImageResource(photoId);
                imageView.setContentDescription(nameText);

                CheckBox checkBox = findViewById(R.id.store_checkBox);
                checkBox.setChecked(isFavorite);
            }

            cursor.close();
            database.close();

        } catch (SQLException ignored) {
            Toast toast = Toast.makeText(this, "Store Activity - Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void ToggleCheckbox(View view) {
        int drinkId = (int) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_STORE_ID);

        CheckBox checkBox = findViewById(R.id.store_checkBox);
        ContentValues values = new ContentValues();
        values.put("favorite", checkBox.isChecked());

        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase database = starbuzzDatabaseHelper.getWritableDatabase();
            database.update(StoreModel.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(drinkId)});
            database.close();
        } catch (SQLException ignored) {
            Toast toast = Toast.makeText(this, "Store Activity - Database unavailable checkbox", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}