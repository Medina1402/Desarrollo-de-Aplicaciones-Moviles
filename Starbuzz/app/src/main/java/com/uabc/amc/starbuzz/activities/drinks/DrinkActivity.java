package com.uabc.amc.starbuzz.activities.drinks;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uabc.amc.starbuzz.R;
import com.uabc.amc.starbuzz.database.StarbuzzDatabaseHelper;
import com.uabc.amc.starbuzz.database.models.DrinkModel;

import java.util.Objects;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINK_ID = "drink_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = (int) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_DRINK_ID);
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);

        try {
            SQLiteDatabase database = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = database.query(
                    DrinkModel.TABLE_NAME,
                    new String[]{"name, description, image_resource_id"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkId)},
                    null, null, null
            );

            if(cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);

                setTitle("Drink - " + nameText);
                TextView name = findViewById(R.id.name);
                name.setText(nameText);

                TextView description = findViewById(R.id.description);
                description.setText(descriptionText);

                ImageView imageView = findViewById(R.id.photo);
                imageView.setImageResource(photoId);
                imageView.setContentDescription(nameText);
            }

            cursor.close();
            database.close();

        } catch (SQLException ignored) {
            Toast toast = Toast.makeText(this, "Drink Activity - Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}