package com.uabc.amc.starbuzz.activities.drinks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.uabc.amc.starbuzz.R;

import java.util.Objects;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_ID = "drink_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkId = (int) Objects.requireNonNull(getIntent().getExtras()).get(EXTRA_DRINK_ID);
        Drink drink = Drink.drinks[drinkId];
        setTitle("Drink - " + drink.getName());

        TextView name = findViewById(R.id.name);
        name.setText(drink.getName());

        TextView description = findViewById(R.id.description);
        description.setText(drink.getDescription());

        ImageView imageView = findViewById(R.id.photo);
        imageView.setImageResource(drink.getImageResourceId());
        imageView.setContentDescription(drink.getName());
    }
}