package com.uabc.amc.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uabc.amc.starbuzz.activities.drinks.DrinkCategoryActivity;
import com.uabc.amc.starbuzz.activities.foods.FoodCategoryActivity;
import com.uabc.amc.starbuzz.activities.stores.StoreCategoryActivity;

public class TopLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_level_activity);
        setTitle(R.string.app_name);

        /*
         * Change item Listener
         */
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

        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }
}