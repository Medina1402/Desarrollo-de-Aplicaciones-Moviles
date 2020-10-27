package com.uabc.amc.starbuzz.activities.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.uabc.amc.starbuzz.R;

import java.util.Objects;

public class FoodActivity extends AppCompatActivity {

    public static final String EXTRA_FOOD_ID = "food_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        int foodId = (int) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_FOOD_ID);
        Food food = Food.foods[foodId];
        setTitle("Food - " + food.getName());

        TextView name = findViewById(R.id.name);
        name.setText(food.getName());

        TextView description = findViewById(R.id.description);
        description.setText(food.getDescription());

        ImageView imageView = findViewById(R.id.photo);
        imageView.setImageResource(food.getImageResourceId());
        imageView.setContentDescription(food.getName());
    }
}