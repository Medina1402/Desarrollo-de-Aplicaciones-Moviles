package com.uabc.amc.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.uabc.amc.starbuzz.activities.drinks.DrinkActivity;
import com.uabc.amc.starbuzz.activities.drinks.DrinkCategoryActivity;
import com.uabc.amc.starbuzz.activities.foods.FoodActivity;
import com.uabc.amc.starbuzz.activities.foods.FoodCategoryActivity;
import com.uabc.amc.starbuzz.activities.stores.StoreActivity;
import com.uabc.amc.starbuzz.activities.stores.StoreCategoryActivity;
import com.uabc.amc.starbuzz.database.StarbuzzDatabaseHelper;
import com.uabc.amc.starbuzz.database.models.DrinkModel;
import com.uabc.amc.starbuzz.database.models.FoodModel;
import com.uabc.amc.starbuzz.database.models.StoreModel;

public class TopLevelActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private Cursor favoriteCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_level_activity);
        setTitle(R.string.app_name);

        /*
        * Change item Listener
        */
        ListView listView = findViewById(R.id.list_options);
        OnClickListenerAdapterView(listView);

        /* Draw Views */
        setupFavoritesViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupFavoritesViews();
    }

    /*
     * Favorites List
     */
    private void setupFavoritesViews() {
        LinearLayout containerFavorite = findViewById(R.id.container_list_favorites);
        containerFavorite.removeAllViews();

        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            database = starbuzzDatabaseHelper.getReadableDatabase();

            addViewFavorites(DrinkModel.TABLE_NAME, containerFavorite, DrinkActivity.class, DrinkActivity.EXTRA_DRINK_ID);
            addViewFavorites(FoodModel.TABLE_NAME, containerFavorite, FoodActivity.class, FoodActivity.EXTRA_FOOD_ID);
            addViewFavorites(StoreModel.TABLE_NAME, containerFavorite, StoreActivity.class, StoreActivity.EXTRA_STORE_ID);

        } catch (SQLException ignored) {
            Toast toast = Toast.makeText(this, "Store Activity - Database unavailable checkbox", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /*
    *
    */
    private void addViewFavorites(String table, LinearLayout layout, Class<?> cls, String EXTRA_ID) {
        favoriteCursor = database.query(table, new String[] {"_id", "name"}, "favorite = 1", null, null, null, null);
        for(int k=0; k<favoriteCursor.getCount(); k++) {
            favoriteCursor.moveToPosition(k);
            addLinearScrollHorizontalFavorites(layout, favoriteCursor.getString(1), favoriteCursor.getInt(0), cls, EXTRA_ID);
        }
    }

    /**
     * Replace ListView for ScrollViewLayout
     */
    private void addLinearScrollHorizontalFavorites(LinearLayout container, String text, final int id, final Class<?> cls, final String EXTRA_ID) {
        Button child = new Button(this);
        child.setText(text);
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopLevelActivity.this, cls);
                intent.putExtra(EXTRA_ID, id);
                startActivity(intent);
            }
        });

        container.addView(child);
    }

    /*
     * Change Listener ListView
     */
    private void OnClickListenerAdapterView(ListView listView) {
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;

                switch (i) {
                    case 0:
                        intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                        break;
                    case 1:
                        intent = new Intent(TopLevelActivity.this, FoodCategoryActivity.class);
                        break;
                    case 2:
                        intent = new Intent(TopLevelActivity.this, StoreCategoryActivity.class);
                        break;
                }

                if(intent != null) startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favoriteCursor.close();
        database.close();
    }
}