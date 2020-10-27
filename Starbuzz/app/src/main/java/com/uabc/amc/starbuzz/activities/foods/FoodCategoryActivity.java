package com.uabc.amc.starbuzz.activities.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.uabc.amc.starbuzz.R;

public class FoodCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);
        setTitle(R.string.title_category_activity_food);

        ArrayAdapter<Food> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Food.foods
        );

        ListView listDrinks = (ListView) findViewById(R.id.list_food);
        listDrinks.setAdapter(listAdapter);

        /*
         * Change item Listener
         */
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FoodCategoryActivity.this, FoodActivity.class);
                intent.putExtra(FoodActivity.EXTRA_FOOD_ID, (int) l);
                startActivity(intent);
            }
        };

        listDrinks.setOnItemClickListener(itemClickListener);

    }
}