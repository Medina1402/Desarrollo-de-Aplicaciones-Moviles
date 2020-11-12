package com.uabc.amc.starbuzz.activities.foods;

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
import com.uabc.amc.starbuzz.activities.FavoriteAsyncTask;
import com.uabc.amc.starbuzz.database.StarbuzzDatabaseHelper;
import com.uabc.amc.starbuzz.database.models.DrinkModel;
import com.uabc.amc.starbuzz.database.models.FoodModel;

import java.util.Objects;

public class FoodActivity extends AppCompatActivity {
    public static final String EXTRA_FOOD_ID = "food_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        int foodId = (int) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_FOOD_ID);
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);

        try {
            SQLiteDatabase database = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = database.query(
                    FoodModel.TABLE_NAME,
                    new String[]{"name, description, image_resource_id, favorite"},
                    "_id = ?",
                    new String[]{Integer.toString(foodId)},
                    null, null, null
            );

            if(cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) > 0 );

                setTitle("Food - " + nameText);
                TextView name = findViewById(R.id.name);
                name.setText(nameText);

                TextView description = findViewById(R.id.description);
                description.setText(descriptionText);

                ImageView imageView = findViewById(R.id.photo);
                imageView.setImageResource(photoId);
                imageView.setContentDescription(nameText);

                CheckBox checkBox = findViewById(R.id.food_checkBox);
                checkBox.setChecked(isFavorite);
            }

            cursor.close();
            database.close();

        } catch (SQLException ignored) {
            Toast toast = Toast.makeText(this, "Food Activity - Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /*
     * Update favorites of table DRINK
     */
    public void ToggleCheckbox(View view) {
        int foodId = (int) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_FOOD_ID);

        new FavoriteAsyncTask(
                findViewById(R.id.food_checkBox),
                this,
                FoodModel.TABLE_NAME
        ).execute(foodId);

//        CheckBox checkBox = findViewById(R.id.food_checkBox);
//        ContentValues values = new ContentValues();
//        values.put("favorite", checkBox.isChecked());
//
//        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
//        try {
//            SQLiteDatabase database = starbuzzDatabaseHelper.getWritableDatabase();
//            database.update(FoodModel.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(drinkId)});
//            database.close();
//        } catch (SQLException ignored) {
//            Toast toast = Toast.makeText(this, "Food Activity - Database unavailable checkbox", Toast.LENGTH_SHORT);
//            toast.show();
//        }
    }
}